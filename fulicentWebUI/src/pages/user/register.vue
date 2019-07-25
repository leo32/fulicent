<template>
  <el-container>
    <el-header>
      <fuliHeader></fuliHeader>
    </el-header>
    <el-container>
      <el-main>
      <register></register>
      </el-main>
    </el-container>
    <el-footer><fulicentFooter></fulicentFooter></el-footer>
  </el-container>
</template>
<script>
import fuliHeader from "@/components/header/index";
import register from "@/components/main/register";
import fulicentFooter from "@/components/footer/footer";
export default {
  components: { fuliHeader,register,fulicentFooter }
};
</script>
<style>
.el-container {
  display: inline;
}
</style>