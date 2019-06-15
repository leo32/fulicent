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
  components: { fuliHeader, top, productList,fulicentFooter }
};
</script>
<style>
.el-container {
  display: inline;
}
</style>