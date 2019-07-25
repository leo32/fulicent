<template>
  <el-container>
    <el-header>
      <fuliHeader></fuliHeader>
    </el-header>
    <el-container>
      <el-main>
      <login></login>
      </el-main>
    </el-container>
    <el-footer><fulicentFooter></fulicentFooter></el-footer>
  </el-container>
</template>
<script>
import fuliHeader from "@/components/header/index";
import login from "@/components/main/login";
import fulicentFooter from "@/components/footer/footer";
export default {
  components: { fuliHeader,login,fulicentFooter }
};
</script>
<style>
.el-container {
  display: inline;
}
</style>