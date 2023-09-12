module.exports = {
    lintOnSave: false, // 关闭eslint校验
    devServer: {
        host: "localhost",
        port: 9282,
        https: false,
        proxy: {
            '/api': {
                // target: 'http://60.205.92.196:3000',
                target: 'http://localhost:9281',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        },
        // proxy: "http://localhost:9281",
        overlay: { // 关闭eslint校验
            warning: false,
            errors: false
        },
    }
}
//设置代理解决跨域问题