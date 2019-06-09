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
    <el-footer>Footer</el-footer>
  </el-container>
</template>
<script>
import fuliHeader from "@/components/header/index";
import category from "@/components/main/category";
import productList from "@/components/main/productList";

export default {
  components: { fuliHeader, category, productList }
};
</script>
<style>
.el-container {
  display: inline;
}
</style>