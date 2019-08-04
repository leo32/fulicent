<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted() {  
    const script = document.createElement('script')  
    script.src = `https://s96.cnzz.com/z_stat.php?id=1277805067&web_id=1277805067`  
    script.language = 'JavaScript'  
    document.body.appendChild(script)  
  }, 
   watch: {  
    '$route': {
      handler(to, from) {
        setTimeout(() => {
          if (window._czc) {  
            let location = window.location; 
            let contentUrl = location.pathname + location.hash;  
            let refererUrl = '/';  
            window._czc.push(['_trackPageview', contentUrl, refererUrl])  
          }
        }, 300) 
      },
      immediate: true
    }  
  }
}

</script>

<style>
#app {
  font-family: Helvetica, sans-serif;
  text-align: center;
}
</style>