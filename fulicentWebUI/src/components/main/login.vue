<template>
  <el-row class="col-main">
    <div class="main-wrap" style="margin-left:0;min-height: 0px;">
      <!--标题 -->
      <div class="am-cf cxx-content-top">
        <div class="am-fl am-cf cxx-content-title"><span class="am-text-danger am-text-lg">用户登录</span></div>
      </div>
      <!--个人信息 -->
      <div class="info-main" style="margin:30px 250px 30px 250px;">
        <el-form name="userForm" class="am-form am-form-horizontal" method="post">

          <div class="am-form-group">
            <label for="user-name2" class="am-form-label"><span class="am-text-danger">*</span>邮箱地址：</label>
            <div class="am-form-content">
              <el-input type="text" v-model="user.email" placeholder="请输入邮箱地址" value=""></el-input>
            </div>
          </div>


          <div class="am-form-group">
            <label for="user-new-password" class="am-form-label">密码：</label>
            <div class="am-form-content">
              <el-input  type="password" v-model="user.password" placeholder="由数字、字母组合"></el-input>
            </div>
          </div>
          <div class="info-btn">
            <el-button class="am-btn am-btn-success am-round cxxia-ajax-submit" @click="submitForm(user)">立即登录</el-button>
          </div>

        </el-form>
      </div>

    </div>
  </el-row>
</template>
<script>
  import  {setToken}  from "@/utils/cookies"
  import  {getUsers}  from "@/api/users"
  import datacenterBus from "@/api/datacenterBus"
  export default {
    data() {
      return {
        user: {
          email: '',
          password: ''
        }
      }
    },
    methods: {
      submitForm(para) {

        this.loading = true
        var user={"email":para.email,"password":para.password}
        getUsers(user).then(response => {
          var data = response.data.userList;
          setToken(data[0].userName)
          this.$router.push({path:'/'})
        }).finally(r => {
          this.loading = false
        });
      }
    }
  }

</script>