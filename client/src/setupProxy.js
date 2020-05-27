const createProxyMiddleware = require('http-proxy-middleware')

console.log(createProxyMiddleware)
module.exports = function(app) {
  app.use(
    createProxyMiddleware('/api', {
      target: 'http://localhost:8080',
      changeOrigin: true,
      pathRewrite: (path) => {
        return path.replace('/api', '/')
      }
    })
  )
}