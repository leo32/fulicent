<template>
  <el-row class="col-main">
    <div class="main-wrap" style="margin-left:0;min-height: 0px;">
      <!--标题 -->
      <div class="am-cf cxx-content-top">
        <div class="am-fl am-cf cxx-content-title"><span class="am-text-danger am-text-lg">用户注册</span></div>
      </div>
      <!--个人信息 -->
      <div class="info-main" style="margin:30px 200px 30px 200px;">
        <el-form name="userForm" class="am-form am-form-horizontal" method="post" >

          <div class="am-form-group">
            <label for="user-name2" class="am-form-label"><span class="am-text-danger">*</span>用户名：</label>
            <div class="am-form-content">
              <el-input v-model="user.userName" placeholder="请输入用户名" value=""></el-input>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-email" class="am-form-label">Email：</label>
            <div class="am-form-content">
              <el-input v-model="user.email" placeholder="Email"></el-input>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-new-password" class="am-form-label">设置密码：</label>
            <div class="am-form-content">
              <el-input type="password" v-model="user.password" auto-complete="off" placeholder="由数字、字母组合"></el-input>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-confirm-password" class="am-form-label">确认密码：</label>
            <div class="am-form-content">
              <el-input type="password" v-model="user.checkPassword" auto-complete="off" placeholder="请再次输入上面的密码">
              </el-input>
            </div>
          </div>
          <div class="info-btn">
            <el-button type="primary" @click="submitForm(user)"
              class="am-btn am-btn-success am-round cxxia-ajax-submit">注册</el-button>
          </div>

        </el-form>
      </div>

    </div>

  </el-row>
</template>
<script>
  import {
    saveUsers
  } from "@/api/users";
  export default {
    data() {
      return {
        user: {
          userName: '',
          email: '',
          password: '',
          checkPassword: ''

        }
      }
    },
    methods: {
      submitForm(para) {

        this.loading = true
        saveUsers(para).then(response => {
          var data = response.data;
        }).finally(r => {
          this.loading = false
        });
      }
    }
  }

</script>
