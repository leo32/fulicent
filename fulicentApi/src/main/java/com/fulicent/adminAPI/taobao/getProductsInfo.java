package com.fulicent.adminAPI.taobao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulicent.api.entity.Brand;
import com.fulicent.api.entity.BrandInfo;
import com.fulicent.common.entity.ApiResponseBody;
import com.fulicent.common.entity.ApiResponseStatus;
import com.fulicent.common.entity.MessageInfo;

@RestController
@RequestMapping("/admin")
public class getProductsInfo {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public void SaveProducts(@RequestParam(name = "limit", defaultValue = "0") int limit,
            @RequestParam(name = "skip", defaultValue = "0") int skip,
            @RequestParam(value = "sort", required = false, defaultValue = "") String sort,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order){
		/*TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "", "");
		TbkUatmFavoritesItemGetRequest  req = new TbkUatmFavoritesItemGetRequest ();
		req.setPlatform(1L);
		req.setPageSize(20L);
		req.setAdzoneId(226L);
		req.setUnid("3456");
		req.setFavoritesId(10010L);
		req.setPageNo(2L);
		req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick,shop_title,zk_final_price_wap,event_start_time,event_end_time,tk_rate,status,type");
		TbkUatmFavoritesItemGetResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
		ResponseEntity<ApiResponseBody> List<Brand> brand=brandService.Brand(limit, skip, sort, order);
		return new ResponseEntity<>(ApiResponseBody.builder()
				.status(new MessageInfo(ApiResponseStatus.RESOURCE_FOUND))
				.data(new BrandInfo(item))
				.build(),
				HttpStatus.OK
				);*/
	}
}
