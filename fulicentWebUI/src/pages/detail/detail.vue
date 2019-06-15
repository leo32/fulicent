<template>
  <el-container>
    <el-header>
      <fuliHeader></fuliHeader>
    </el-header>
    <el-container>
      <el-main>
        <top></top>
      </el-main>
      <el-main>
        <detail></detail>
      </el-main>
     <el-main>
        <productList  classType="guess"></productList>
      </el-main>
    </el-container>
    <el-footer><fulicentFooter></fulicentFooter></el-footer>
  </el-container>
</template>
<script>
import fuliHeader from "@/components/header/index";
import top from "@/components/main/top";
import Detail from "@/components/main/detail";
import fulicentFooter from "@/components/footer/footer";
import productList from "@/components/main/productList";
export default {
  components: { fuliHeader, top, Detail,productList,fulicentFooter }
};
</script>
<style>
.el-container {
  display: inline;
}
</style>