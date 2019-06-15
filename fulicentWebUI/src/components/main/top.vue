<template>
  <el-row class="cate-box">
    <div class="tab-area">
      <div class="wrapper hori-cate-area">
        <div class="cate-l-1">
          <div class="wrapper">
            <a href="javascript:void(0);" v-on:click="refreshProducts()" class="active"><i class="cate-icon"></i>
              全部分类</a>
            <a class="" href="javascript:void(0);" v-on:click="refreshProducts(item.id)" v-for="item in categoryList"
              :key="item.id">{{item.name}}</a>
          </div>
        </div>
      </div>
      <div class="wrapper">
        <div class="order-area">
          <ul class="sort-type">
            <li data-sort="default" style="padding-left: 15px;"><i class="cate-icon" style="font-size:14px"></i> 排序筛选
            </li>
            <li data-sort="default" class="active">默认 <i class="cate-icon" style="font-size:14px"></i></li>
            <li data-sort="new" class="">最新 <i class="cate-icon" style="font-size:14px"></i></li>
            <li data-sort="volume" class="">销量 <i class="cate-icon" style="font-size:14px"></i></li>
            <li data-sort="minPrice" class="">价格 <i class="cate-icon" style="font-size:14px"></i></li>
            <li data-sort="quan" class="">券额 <i class="cate-icon" style="font-size:14px"></i></li>
            <li data-sort="receive" class="">剩余 <i class="cate-icon" style="font-size:14px"></i></li>
          </ul>
          <ul class="price-filter">
            <li data-price="2" class=""><span><i></i></span>2元券</li>
            <li data-price="5" class=""><span><i></i></span>5元券</li>
            <li data-price="8" class=""><span><i></i></span>8元券</li>
          </ul>
        </div>
      </div>
    </div>
  </el-row>
</template>
<script>
  import {
    getCategoryList
  } from "@/api/category";
  import datacenterBus from "@/api/datacenterBus";
  export default {
    data() {
      return {
        categoryList: []
      };
    },
    created() {
      this.bindCategoryList();
    },
    methods: {
      bindCategoryList() {
        getCategoryList().then(response => {
          this.categoryList = response.data.categoryList;
        });
      },
      refreshProducts(id) {
        datacenterBus.$emit("getValue", id);
      }
    }
  };

</script>
