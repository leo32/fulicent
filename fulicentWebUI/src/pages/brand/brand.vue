<template>
  <el-container>
    <el-header>
      <fuliHeader classType="brand"></fuliHeader>
    </el-header>
    <el-container>
      <el-main>
        <brandList></brandList>
      </el-main>
    </el-container>
    <el-footer><fulicentFooter></fulicentFooter></el-footer>
  </el-container>
</template>
<script>
import fuliHeader from "@/components/header/index";
import category from "@/components/main/category";
import brandList from "@/components/main/brand";
import fulicentFooter from "@/components/footer/footer";
export default {
  components: { fuliHeader, category, brandList,fulicentFooter }
};
</script>
<style>
.el-container {
  display: inline;
}
</style>