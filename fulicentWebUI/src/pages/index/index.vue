<template>
  <el-container>
    <el-header>
      <fuliHeader></fuliHeader>
    </el-header>
    <el-container>
      <el-main>
        <category></category>
      </el-main>
      <el-main>
        <productList></productList>
      </el-main>
    </el-container>
    <el-footer>
      <fulicentFooter></fulicentFooter>
    </el-footer>
  </el-container>
</template>
<script>
  import fuliHeader from "@/components/header/index";
  import category from "@/components/main/category";
  import productList from "@/components/main/productList";
  import fulicentFooter from "@/components/footer/footer";
  export default {
    metaInfo: {
      title: '福利省钱网,优惠券领取,抖音神器',
      meta: [{
        name: '优惠券领取',
        content: '抖音神器'
      }],
      link: [{
        rel: 'asstes',
        href: 'http://fulicent.com/'
      }]
    },
    components: {
      fuliHeader,
      category,
      productList,
      fulicentFooter
    }
  };

</script>
<style>
  .el-container {
    display: inline;
  }

</style>
