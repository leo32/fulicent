<template>
  <el-row style="width:1100px;margin-right:auto;margin-left:auto;">

    <div class="tab-area">
      <div class="wrapper hori-cate-area">
        <div class="cate-l-1">
          <div class="wrapper">
            <a href="javascript:void(0);" v-on:click="refreshBrands()" class="active"><i class="cate-icon"></i>
              全部分类</a>
            <a class="" href="javascript:void(0);" v-on:click="refreshBrands(item.id)" v-for="item in categoryList"
              :key="item.id">{{item.name}}</a>
          </div>
        </div>
      </div>
    </div>
    <div class="fwrap  rel-zk-area">
      <p class="head" style="margin-bottom: -20px;"><span>品牌优惠券</span></p>
      <div class="fbrand indexblock" v-loading="loading" id="floor1" v-for="item in brandsList" :key="item.id">
        <ul class="cl">
          <li>
            <div class="floor">
              <h2>{{item.id}}F</h2>
              <h4>{{item.name}}</h4>
            </div>
          </li>
          <li v-for="chileItem in item.brandList" :key="chileItem.id"><a class="brandA"
              :href="['/?brandId='+chileItem.id]" target="_blank" :title="chileItem.Name">
              <div class="brandcell"><img :src="chileItem.images" :alt="chileItem.Name"
                  :title="chileItem.Name"><span>{{chileItem.name}}</span></div>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </el-row>
</template>
<script>
  import {
    getBrandList
  } from "@/api/brand";
  import {
    getCategoryList
  } from "@/api/category";
  import datacenterBus from "@/api/datacenterBus";
  export default {
    data() {
      return {
        loading:false,
        brandsList: [],
        categoryList: []
      };
    },
    created() {
      this.bindBrandList();
      this.bindCategoryList();
    },
    methods: {
      bindBrandList(params) {
        this.loading = true;
        getBrandList(params).then(response => {
          this.brandsList = response.data.brandsList;
        }).finally(r => {
          this.loading = false;
        });
      },
      bindCategoryList() {
        getCategoryList().then(response => {
          this.categoryList = response.data.categoryList;
        });
      },
      refreshBrands(id) {
        let params = {
          categoryId: id
        }
        this.bindBrandList(params);
      },
      goProducts(id) {
        this.$router.push({
          path: '/',
          query: {
            brandId: id
          }
        })
      }
    }
  };

</script>
<style>
  .indexblock {
    margin: 20px auto;
    padding: 5px 15px 0;
    background: #fff;
  }

  .floor {
    width: 151px;
    height: 98px;
    background: url(/Public/static/tqkpc/images/floor.png) no-repeat center;
    position: relative;
  }

  .brandcell {
    width: 151px;
    height: 98px;
    position: relative;
    cursor: pointer;
    text-align: center;
  }

  .fbrand ul {
    width: 100%;
    border-left: 1px solid #eee;
    border-top: 1px solid #eee;
  }

  .fbrand ul li {
    float: left;
    border-right: 1px solid #eee;
    border-bottom: 1px solid #eee;
  }

  .fwrap {
    padding: 5px 0;
  }

  .brandcell img {
    height: 60px;
    object-fit: cover;
    padding-top: 15px;
  }

  .brandcell span {
    display: none;
    width: 146px;
    height: 98px;
    background: rgba(0, 0, 0, 0.6);
    font-size: 24px;
    color: #fff;
    text-align: center;
    font-weight: bold;
    line-height: 98px;
    position: absolute;
    left: 0;
    top: 0;
    -webkit-transition: All .5s ease;
    -moz-transition: All .5s ease;
    -o-transition: All .5s ease;
  }

  .cl:after,
  .clearfix:after {
    content: "\20";
    display: block;
    height: 0;
    clear: both;
    visibility: hidden;
  }

  .container {
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
  }

  .brandA:hover .brandcell span {
    display: block;
  }

</style>
