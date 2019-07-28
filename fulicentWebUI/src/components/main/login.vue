<template>
  <el-row class="col-main">
    <div class="main-wrap" style="margin-left:0;min-height: 0px;">
      <!--标题 -->
      <div class="am-cf cxx-content-top">
        <div class="am-fl am-cf cxx-content-title"><span class="am-text-danger am-text-lg">用户登录</span></div>
      </div>
      <!--个人信息 -->
      <div class="info-main" style="margin:30px 250px 30px 250px;">
        <el-form :model="user" :rules="rules" ref="user" name="user" class="am-form am-form-horizontal" method="post">
          <div class="am-form-group">
            <label for="user-name2" class="am-form-label"><span class="am-text-danger">*</span>邮箱地址：</label>
            <div class="am-form-content">
              <el-form-item label="email" prop="email">
                <el-input type="text" v-model="user.email" placeholder="请输入邮箱地址"></el-input>
              </el-form-item>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-new-password" class="am-form-label">密码：</label>
            <div class="am-form-content">
              <el-form-item label="password" prop="password">
                <el-input type="password" v-model="user.password" placeholder="由数字、字母组合"></el-input>
              </el-form-item>
            </div>
          </div>
          <div class="info-btn">
            <el-button class="am-btn am-btn-success am-round cxxia-ajax-submit" @click="submitForm(user)">立即登录
            </el-button>
          </div>
        </el-form>
      </div>

    </div>
  </el-row>
</template>
<script>
  import {
    setToken
  } from "@/utils/cookies"
  import {
    getUsers
  } from "@/api/users"
  import {
    Message
  } from 'element-ui'
  import datacenterBus from "@/api/datacenterBus"
  export default {
    data() {
      return {
        user: {
          email: '',
          password: ''
        },
        rules: {
          email: [{
              required: true,
              message: '请输入邮箱号',
              trigger: 'blur'
            },
            {
              validator: function (rule, value, callback) {
                if (value === '') {
                  callback(new Error('请正确填写邮箱'));
                } else {
                  if (value != '') {
                    var reg = /^[A-Za-z0-9\u4e00-\u9fa5\.]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                    if (!reg.test(value)) {
                      callback(new Error('请输入有效的邮箱'));
                    } else {
                      callback();
                    }
                  } else {
                    callback(new Error('请填写邮箱'));
                  }
                }
              },
              trigger: 'blur'
            }
          ],
          password: [{
              required: true,
              message: '请输入密码',
              trigger: 'blur'
            },
            {
              min: 5,
              max: 25,
              message: '长度在 5 到 25个字符'
            },
            {
              pattern: /^(\w){5,25}$/,
              message: '只能输入5-25个字母、数字、下划线'
            }
          ]
        }
      }
    },
    methods: {
      submitForm(para) {
        let self=this;
        self.$refs['user'].validate((valid) => {
          if (valid) {
            self.loading = true
            var user = {
              "email": para.email,
              "password": para.password
            }
            getUsers(user).then(response => {
              var data = response.data.userList;
              setToken(data[0].userName)
              Message({
                message: '登录成功！',
                type: 'success',
                duration: 5 * 1000
              });
              Message.close = function () {
                self.$router.push({
                  path: '/'
                })
              }
            }).finally(r => {
              self.loading = false
            });
          }
        })
      }
    }
  }

</script>
