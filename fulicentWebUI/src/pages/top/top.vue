<template>
  <el-container>
    <el-header>
      <fuliHeader classType="top"></fuliHeader>
    </el-header>
    <el-container>
      <el-main>
        <top></top>
      </el-main>
      <el-main>
        <productList classType="top"></productList>
      </el-main>
    </el-container>
    <el-footer><fulicentFooter></fulicentFooter></el-footer>
  </el-container>
</template>
<script>
import fuliHeader from "@/components/header/index";
import top from "@/components/main/top";
import productList from "@/components/main/productList";
import fulicentFooter from "@/components/footer/footer";

export default {
      metaInfo: {
      title: '福利省钱网,优惠券领取,超级人气榜',
      meta: [{
        name: '优惠券领取',
        content: '福利省钱网,超级人气榜'
      }],
      link: [{
        rel: 'asstes',
        href: 'http://fulicent.com/top'
      }]
    },
  components: { fuliHeader, top, productList,fulicentFooter }
};
</script>
<style>
.el-container {
  display: inline;
}
</style>