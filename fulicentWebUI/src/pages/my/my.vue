<template>
  <el-container>
    <el-header>
      <fuliHeader classType="my"></fuliHeader>
    </el-header>
    <el-container>
      <el-main>
        <top></top>
      </el-main>
      <el-main>
        <productList classType="my"></productList>
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
  import top from "@/components/main/top";
  export default {
    metaInfo: {
      title: '福利省钱网,优惠券领取,我的足迹',
      meta: [{
        name: '优惠券领取',
        content: '福利省钱网,我的足迹'
      }],
      link: [{
        rel: 'asstes',
        href: 'http://fulicent.com/my'
      }]
    },
    components: {
      top,
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
