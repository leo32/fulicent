
// 设置静态资源的线上路径
const publicPath = './';
// 设置api请求前缀
const apiPrefix = 'http://localhost:8080';

cml.config.merge({
  templateLang: "cml",
  templateType: "html",
  platforms: ["web"],
  projectName:"index",
  console:true,
  hash:false,
  buildInfo: {
    wxAppId: '123456'
  },  
  baseStyle: {
    wx: false,
    alipay: false,
    baidu: false,
    web: true,
    weex: false
  },
  web: {
    dev: {
      analysis: false,
      console: false
    },
    build: {
	  hash:false,
      analysis: false,
      publicPath: `${publicPath}`,
      apiPrefix
    }
  }
})

