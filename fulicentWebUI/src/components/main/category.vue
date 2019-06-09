<template>
  <el-row class="wrapper home-oper-area">
    <el-col class="cate-area">
      <a class="cate-item" href="javascript:void(0);" v-on:click="refreshProducts(item.id)" v-for="item in categoryList" :key="item.id">
        <div class="inner">
          <i class="cate-icon"></i>
          {{item.name}}
        </div>
      </a>
    </el-col>
    <el-col>
      <el-row class="banner-top">
        <el-col></el-col>
        <el-col></el-col>
      </el-row>
      <el-row class="small-banner-area">
        <el-col>
          <div href="javascript:;" class="small-banner-item">
            <a target="_blank" href="/index/index/index.html">
              <img
                src="http://quan.fulibus.net/data/uploads/banner/20170524/a8283a0300dd1c29e699a56cc9c71b00.jpg"
              >
              <p class="title">新款短袖</p>
            </a>
          </div>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>
<script>
import { getCategoryList } from "@/api/category";
import datacenterBus from "@/api/datacenterBus";
export default {
  data() {
    return { categoryList: [] };
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
    refreshProducts(id){
      datacenterBus.$emit("getValue",id);
    }
  }
};
</script>