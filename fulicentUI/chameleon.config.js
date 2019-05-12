
// 设置静态资源的线上路径
const publicPath = './';
// 设置api请求前缀
const apiPrefix = 'https://api.chameleon.com';

cml.config.merge({
  templateLang: "vue",
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
  wx: {
    dev: {
    },
    build: {
      apiPrefix
    }
  },
  web: {
    dev: {
      analysis: false,
      console: false
    },
    build: {
      analysis: false,
      publicPath: `${publicPath}`,
      apiPrefix
    }
  },
  weex: {
    dev: {
    },
    build: {
      publicPath: `${publicPath}/weex/`,
      apiPrefix
    },
    custom: {
      publicPath: `${publicPath}/wx/`,
      apiPrefix
    }
  }
})

