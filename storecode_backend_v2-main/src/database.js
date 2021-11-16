const mysql = require('mysql')

const mysqlConnection= mysql.createConnection(
    {
        host: '127.0.0.1',
        port: '3306',
        user: 'root',
        password: '1234',
        database: 'storecode_movil'
    }
)


mysqlConnection.connect(function(err){
    if(err){
        console.log(err);
        return
    }else{
        console.log('La base de datos esta conectada')
    }
})

module.exports = mysqlConnection;