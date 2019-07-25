package com.fulicent.common.entity;

import java.util.Date;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fulicent.api.entity.Products;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LuceneDoc {
	private Long id;
	private String name;
	private String tag;
	private String links;
	private String coupon;
	private String expire;
	private String image;
	private double sale;
	private int couponRemainCount;
	private double couponPrice;
	
	public Document toDocument(){
        Document doc = new Document();
        doc.add(new LongPoint("id", id));
        doc.add(new StoredField("id", id));
        doc.add(new NumericDocValuesField("sortid", id));
        doc.add(new TextField("name", name, Store.YES));
        doc.add(new TextField("tag", tag, Store.YES));
        
        doc.add(new StoredField("links", links));
        doc.add(new StoredField("coupon", coupon));
        doc.add(new StoredField("expire", expire));
        doc.add(new StoredField("image", image));
        doc.add(new StoredField("sale", sale));
        doc.add(new StoredField("couponRemainCount", couponRemainCount));
        doc.add(new StoredField("couponPrice", couponPrice));
        return doc;
	}
	
	public static LuceneDoc parseDoc(Document doc){
		LuceneDoc luceneDoc=new LuceneDoc();
		luceneDoc.setId(Long.parseLong(doc.get("id")));
		luceneDoc.setName(doc.get("name"));
		luceneDoc.setTag(doc.get("tag"));
		
		luceneDoc.setLinks(doc.get("links"));
		luceneDoc.setCoupon(doc.get("coupon"));
		luceneDoc.setExpire(doc.get("expire"));
		luceneDoc.setImage(doc.get("image"));
		luceneDoc.setSale(Double.parseDouble(doc.get("sale")));
		luceneDoc.setCouponRemainCount(Integer.parseInt(doc.get("couponRemainCount")));
		luceneDoc.setCouponPrice(Double.parseDouble(doc.get("couponPrice")));
		return luceneDoc;
	}
}
