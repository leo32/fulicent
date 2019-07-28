<template>
  <el-row class="col-main">
    <div class="main-wrap" style="margin-left:0;min-height: 0px;">
      <!--标题 -->
      <div class="am-cf cxx-content-top">
        <div class="am-fl am-cf cxx-content-title"><span class="am-text-danger am-text-lg">用户注册</span></div>
      </div>
      <!--个人信息 -->
      <div class="info-main" style="margin:30px 200px 30px 200px;">
        <el-form :model="user" name="user" ref="user" class="am-form am-form-horizontal" method="post" :rules="rules">
          <div class="am-form-group">
            <label for="user-name2" class="am-form-label"><span class="am-text-danger">*</span>用户名：</label>
            <div class="am-form-content">
              <el-form-item label="user name" prop="userName">
                <el-input v-model="user.userName" placeholder="请输入用户名"></el-input>
              </el-form-item>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-email" class="am-form-label">Email：</label>
            <div class="am-form-content">
              <el-form-item label="email" prop="email">
                <el-input v-model="user.email" placeholder="Email"></el-input>
              </el-form-item>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-new-password" class="am-form-label">设置密码：</label>
            <div class="am-form-content">
              <el-form-item label="password" prop="password">
                <el-input type="password" v-model="user.password" auto-complete="off" placeholder="由数字、字母组合"></el-input>
              </el-form-item>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-confirm-password" class="am-form-label">确认密码：</label>
            <div class="am-form-content">
              <el-form-item label="password" prop="checkPassword">
                <el-input type="password" v-model="user.checkPassword" auto-complete="off" placeholder="请再次输入上面的密码">
                </el-input>
              </el-form-item>
            </div>
          </div>
          <div class="info-btn">
            <el-form-item>
              <el-button type="primary" @click="submitForm(user)"
                class="am-btn am-btn-success am-round cxxia-ajax-submit">注册</el-button>
            </el-form-item>
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
  import {
    Message
  } from 'element-ui'
  export default {
    data() {
      let self = this;
      return {
        user: {
          userName: '',
          email: '',
          password: '',
          checkPassword: ''

        },
        rules: {
          userName: [{
              required: true,
              message: '请输入用户名',
              trigger: 'blur'
            },
            {
              min: 5,
              max: 10,
              message: '长度在 5 到 10 个字符'
            },
            {
              pattern: /^(\w){5,10}$/,
              message: '长度在 5 到 10 个字符之间， 只能包含字符、数字和下划线'
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
          ],
          checkPassword: [{
              required: true,
              message: '请再次输入密码',
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
            },
            {
              validator: function (rule, value, callback) {
                if (!(value === self.user.password)) {
                  callback(new Error("两次输入的密码不一致"));
                } else {
                  callback();
                }
              },
              trigger: 'blur'
            }
          ],
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
        }
      }
    },
    methods: {
      submitForm(para) {
        let self=this;
        self.$refs['user'].validate((valid) => {
          if (valid) {
            self.loading = true
            saveUsers(para).then(response => {
              var data = response.data;
              Message({
                message: '注册成功,请登录邮箱激活！',
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
        });
      }
    }
  }

</script>
