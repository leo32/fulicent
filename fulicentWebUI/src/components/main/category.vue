<template>
  <el-row class="wrapper home-oper-area">
    <div class="cate-area">
      <a class="cate-item" href="javascript:void(0);" v-loading="loading" v-on:click="refreshProducts(item.id)"
        v-for="item in categoryList" :key="item.id">
        <div class="inner">
          <i class="cate-icon">{{getIcon(item.id)}}</i>
          {{item.name}}
        </div>
      </a>
    </div>
    <div class="banner-top">
      <div class="banner-area swiper-container swiper-container-horizontal">
        <swiper :options="swiperOption">
          <swiper-slide v-for="item in ad1Products" :key="item.id">
            <div><a target="_blank" :href="['/#/detail/'+item.id ]">
                <img :src="item.image" style="height:200px;line-height:200px;">
              </a></div>
          </swiper-slide>
          <div class="swiper-pagination" slot="pagination"></div>
        </swiper>
      </div>
      <div class="top-right-banner">
        <div href="javascript:;" class="small-banner-item" v-for="item in ad3Products" :key="item.id">
          <a target="_blank" :href="['/#/detail/'+item.id]">
            <img :src="item.image">
            <p class="title">{{item.name}}</p>
          </a>
        </div>
      </div>
    </div>
    <div class="small-banner-area">
      <swiper :options="bottomSwiperOption">
        <swiper-slide v-for="item in ad2Products" :key="item.id">
          <a :href="['/#/detail/'+item.id]" target="_blank">
            <img :alt="item.name" :data-original="item.image" :src="item.image"  style="height:130px;line-height:130px;">
          </a>
        </swiper-slide>
        <div class="swiper-button-prev" slot="button-prev"></div>
        <div class="swiper-button-next" slot="button-next"></div>
      </swiper>
    </div>
  </el-row>
</template>
<script>
  import {
    swiper,
    swiperSlide
  } from 'vue-awesome-swiper'
  import 'swiper/dist/css/swiper.css'
  import {
    getCategoryList
  } from "@/api/category";
  import datacenterBus from "@/api/datacenterBus";
  import {
    getAdProducts
  }
  from "@/api/products";

  export default {
    data() {
      return {
        loading: false,
        categoryList: [],
        ad1Products: [],
        ad2Products: [],
        ad3Products: [],
        swiperOption: {
          spaceBetween: 10,
          centeredSlides: true,
          autoplay: {
            delay: 2500,
            disableOnInteraction: false
          },
          pagination: {
            el: '.swiper-pagination',
            clickable: true
          }
        },
        bottomSwiperOption: {
          slidesPerView: 6,
          spaceBetween: 10,
          slidesPerGroup: 3,
          loop: true,
          loopFillGroupWithBlank: true,
          pagination: {
            el: '.swiper-pagination',
            clickable: true
          },
          navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev'
          }
        }
      };
    },
    components: {
      swiper,
      swiperSlide
    },
    created() {
      this.bindCategoryList();
      this.bindAdProducts();
    },
    methods: {
      bindCategoryList() {
        this.loading = true
        getCategoryList().then(response => {
          this.categoryList = response.data.categoryList;
        }).finally(r => {
          this.loading = false
        });
      },
      refreshProducts(id) {
        datacenterBus.$emit("getValue", id)
      },
      bindAdProducts() {
        this.loading = true;
        getAdProducts().then(response => {
          this.ad1Products = response.data.ad1ProductList;
          this.ad2Products = response.data.ad2ProductList;
          this.ad3Products = response.data.ad3ProductList;
        }).finally(r => {
          this.loading = false
        });
      },
      getIcon(id) {
        var icon;
        switch (id) {
          case "1":
            icon = '';
            break;
          case "2":
            icon = "";
            break;
          case "3":
            icon = "";
            break;
          case "4":
            icon = "";
            break;
          case "5":
            icon = "";
            break;
          case "6":
            icon = " ";
            break;
          case "7":
            icon = "";
            break;
          case "8":
            icon = "";
            break;
          case "9":
            icon = "";
            break;
          case "10":
            icon = "";
            break;
          case "11":
            icon = "";
            break;
          case "12":
            icon = "";
            break;
          case "13":
            icon = "";
            break;
          case "14":
            icon = "";
            break;
        }
        return icon;
      }
    }
  };

</script>
<style scoped>


</style>
