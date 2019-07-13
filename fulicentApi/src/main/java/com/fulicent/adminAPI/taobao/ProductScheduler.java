package com.fulicent.adminAPI.taobao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fulicent.api.entity.Category;
import com.fulicent.api.entity.Products;
import com.fulicent.api.service.CategoryService;
import com.fulicent.api.service.ProductsService;
import com.fulicent.common.entity.ApiResponseBody;
import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.entity.MessageInfo;
import com.fulicent.common.entity.Pagination;
import com.fulicent.common.utils.JsonUtils;
import com.fulicent.common.utils.StringUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkUatmFavoritesGetRequest;
import com.taobao.api.request.TbkUatmFavoritesItemGetRequest;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkUatmFavoritesGetResponse;
import com.taobao.api.response.TbkUatmFavoritesItemGetResponse;

@Component
@RestController
@RequestMapping("/admin/refreshProduct")
public class ProductScheduler {

	private CategoryService categoryService;
	private ProductsService productsService;
	
	@Inject
	public ProductScheduler(CategoryService categoryService,ProductsService productsService){
		this.categoryService=categoryService;
		this.productsService=productsService;
	}
	
	
	@Scheduled(cron = "59 59 23,9 * * *")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ApiResponseBody> SaveProducts() throws ApiException{
		try{
			TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "", "");
			TbkUatmFavoritesGetRequest req = new TbkUatmFavoritesGetRequest(); req.setFields("favorites_title,favorites_id,type"); 
			TbkUatmFavoritesGetResponse response = client.execute(req);
			JsonNode jn=JsonUtils.jsonNodeOf(response.getBody());
			List<JsonNode> favoritesIds=jn.findValues("favorites_id");
			
			List<Category> category=this.categoryService.Category(0, 0, "", "");
			HashMap<String,Integer> categoryHash=new HashMap<>();
			for (Category c:category){
				if(c.getTaobaoCat()!=null){
					String[] cat=c.getTaobaoCat().split("/");
					for(int i=0;i<cat.length;i++){
						categoryHash.put(cat[i], c.getId());
					}
				}
			}
			Pagination pagination = new Pagination(0, 1, 0);
			List<Products> products=this.productsService.Products(0, 0, "CreateTime", "desc", "", "", "", "", "", pagination);
			HashMap<String,Products> productHash=new HashMap<>();
			for(Products p:products)
			{
				productHash.put(p.getNumIID(), p);
			}
			
			for(int j=0;j<favoritesIds.size();j++){
				Thread.sleep(1000*3);
				String needHandleId=favoritesIds.get(j).asText();
				if(!needHandleId.isEmpty()){
					TbkUatmFavoritesItemGetRequest favoritesItem = new TbkUatmFavoritesItemGetRequest();
					favoritesItem.setPlatform(1L);
					favoritesItem.setAdzoneId();
					favoritesItem.setPageSize(100L);
					favoritesItem.setFavoritesId(Long.valueOf(needHandleId));
					favoritesItem.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,click_url,nick,seller_id,volume,tk_rate,zk_final_price_wap,shop_title,event_start_time,event_end_time,type,status,category,coupon_click_url,coupon_end_time,coupon_info,coupon_start_time,coupon_total_count,coupon_remain_count");
					TbkUatmFavoritesItemGetResponse favoritesItemResponse = client.execute(favoritesItem);
					jn=JsonUtils.jsonNodeOf(favoritesItemResponse.getBody());
					JsonNode items=jn.findValue("uatm_tbk_item");
					
					if(items!=null){
						items.forEach(item->{
							Products p= new Products();
							String numIID=item.findValue("num_iid").asText();
							try{
								Thread.sleep(1000*3);
								if(productHash.get(numIID)==null) 
								{
									
									p.setNumIID(numIID);
									p.setSale(item.findValue("zk_final_price").asDouble());
									if(!StringUtils.IsEmpty(item.findValue("coupon_info"))){
										String coupon=item.findValue("coupon_info").asText();
										int c=coupon.indexOf("减");
										if(c>-1){
											coupon=coupon.split("减")[1].replace("元","").replace("减","");
										}else{
											coupon=coupon.replace("元无条件券","");
										}
										p.setCoupon(coupon);
									}else{
										p.setCoupon("0");
									}
									if(StringUtils.IsNumber(p.getCoupon())){
										BigDecimal sale = new BigDecimal(String.valueOf(p.getSale()));
								        BigDecimal coupon = new BigDecimal(p.getCoupon());
										p.setCouponPrice(sale.subtract(coupon).doubleValue());
									}else{
										System.out.println("getCoupon:"+p.getCoupon()+" num_iid:"+p.getNumIID());
									}
				
									if(!StringUtils.IsEmpty(item.findValue("coupon_click_url"))){
										p.setLinks(item.findValue("coupon_click_url").asText());//click_url
									}
									else{
										if(!StringUtils.IsEmpty(item.findValue("click_url"))){
											p.setLinks(item.findValue("click_url").asText());
										}else{
											p.setLinks(item.findValue("item_url").asText());
										}
									}
									if(!StringUtils.IsEmpty(item.findValue("coupon_remain_count"))){
										BigDecimal totalCount = new BigDecimal(item.findValue("coupon_total_count").asText());
								        BigDecimal remainCount = new BigDecimal(item.findValue("coupon_remain_count").asText());
										p.setCouponRemainCount(totalCount.subtract(remainCount).intValue());
									}else{
										p.setCouponRemainCount((int)(1+Math.random()*(10-1+1)));
									}					
									if(!StringUtils.IsEmpty(item.findValue("coupon_end_time"))){
										p.setExpire(item.findValue("coupon_end_time").asText());
									}
									if(!StringUtils.IsEmpty(item.findValue("small_images"))){
										p.setSmallImages(item.findValue("small_images").toString());
									}
									
									p.setReservePrice(item.findValue("reserve_price").asDouble());
									p.setImage(item.findValue("pict_url").asText());
									p.setStatus(item.findValue("status").asInt());
									p.setName(item.findValue("title").asText());
									
									System.out.println("Name:"+item.findValue("title").asText() +" num_iid:"+p.getNumIID());
									TbkItemInfoGetRequest itemInfo = new TbkItemInfoGetRequest();
									itemInfo.setNumIids(item.findValue("num_iid").asText());
									TbkItemInfoGetResponse itemInfoResponse = client.execute(itemInfo);
									JsonNode json=JsonUtils.jsonNodeOf(itemInfoResponse.getBody());
									JsonNode cat_name=json.findValue("cat_name");
									Thread.sleep(1000*3);
									if(cat_name!=null){
										String[] cat=cat_name.asText().split("/");
										int id=0;
										for(int i=0;i<cat.length;i++){
											if(categoryHash.get(cat[i])!=null){
												id=categoryHash.get(cat[i]);
												break;
											}
										}
										if(id>0){
											p.setCategoryId(String.valueOf(id));
											this.productsService.SaveProduct(p);
										}else{
											System.out.println("cat_name:"+cat_name.asText());
											Category cate=new Category();
											cate.setName(cat_name.asText());
											cate.setStatus(1);
											cate.setImage("");
											cate.setTaobaoCat(cat_name.asText());
											p.setCategoryId(String.valueOf(categoryService.SaveCategory(cate)));
										}
									}
									else{
										System.out.println("cat_name:"+cat_name +" Id:"+p.getId() +" Name:"+p.getName());
										p.setCategoryId("14");
										this.productsService.SaveProduct(p);
									}
								}else{//update
									p=productHash.get(numIID);
									if(!StringUtils.IsEmpty(item.findValue("coupon_remain_count"))){
										BigDecimal totalCount = new BigDecimal(item.findValue("coupon_total_count").asText());
								        BigDecimal remainCount = new BigDecimal(item.findValue("coupon_remain_count").asText());
										p.setCouponRemainCount(totalCount.subtract(remainCount).intValue());
									}else{
										p.setCouponRemainCount((int)(1+Math.random()*(10-1+1)));
									}
									if(item.findValue("status")!=null){
										p.setStatus(item.findValue("status").asInt());
									}
									
									TbkItemInfoGetRequest itemInfo = new TbkItemInfoGetRequest();//need to improve
									itemInfo.setNumIids(numIID);
									TbkItemInfoGetResponse itemInfoResponse = client.execute(itemInfo);
									JsonNode json=JsonUtils.jsonNodeOf(itemInfoResponse.getBody());
									JsonNode cat_name=json.findValue("cat_name");
									Thread.sleep(1000*3);
									if(cat_name!=null){
										String[] cat=cat_name.asText().split("/");
										int id=0;
										for(int i=0;i<cat.length;i++){
											if(categoryHash.get(cat[i])!=null){
												id=categoryHash.get(cat[i]);
												break;
											}
										}
										if(id>0){
											p.setCategoryId(String.valueOf(id));
										}
									}
									this.productsService.UpdateProduct(p);
								}
							}
							catch(Exception e){
								System.out.println("Error:"+e );
							}
						});
						
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Error:"+e );
		}
		return new ResponseEntity<>(ApiResponseBody.builder()
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.build(),
				HttpStatus.OK
				);
	}
	
}
