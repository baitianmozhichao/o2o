//原型链，覆盖toLocaleString()
Date.prototype.toLocaleString = function () {
    var year = this.getFullYear();
    var month = this.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var day = this.getDate();
    if (day < 10) {
        day = "0" + day;
    }
    return year + "-" + month + "-" + day;
};