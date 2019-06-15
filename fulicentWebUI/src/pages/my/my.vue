<template>
  <el-container>
    <el-header>
      <fuliHeader  classType="my"></fuliHeader>
    </el-header>
    <el-container>
      <el-main>
        <category></category>
      </el-main>
      <el-main>
        <productList  classType="my"></productList>
      </el-main>
    </el-container>
    <el-footer><fulicentFooter></fulicentFooter></el-footer>
  </el-container>
</template>
<script>
import fuliHeader from "@/components/header/index";
import category from "@/components/main/category";
import productList from "@/components/main/productList";
import fulicentFooter from "@/components/footer/footer";
export default {
  components: { fuliHeader, category, productList,fulicentFooter }
};
</script>
<style>
.el-container {
  display: inline;
}
</style>