function askVerifyCode() {
    $.get('http://localhost:8080/api/user/send-verify-code', {
        email: $("#email").val()
    }, function (data) {
        alert(data);
    })
}

function register() {
    checkRepeatPassword();
    $.post('http://localhost:8080/api/user/register-verify', {
        username: $("#username").val(),
        password: $("#password").val(),
        email: $("#email").val(),
        verifyCode: $("#verifyCode").val(),
    }, function (data) {
        if (data === "注册成功") {
            alert('注册成功');
            window.location = "http://localhost:8080/login.html";
        } else {
            alert(data);
        }
    })
}

function login() {
    $.post('http://localhost:8080/api/user/login-verify', {
        username: $("#username").val(),
        password: $("#password").val(),
    }, function (data) {
        if (data.includes('登录成功')) {
            alert(data + '欢迎回来！')
            window.location = "http://localhost:8080/index.html";
        } else {
            alert(data);
        }
    })
}

function loginAdmin() {
    $.post('http://localhost:8080/admin/admin-login', {
        username: $("#username").val(),
        password: $("#password").val(),
    }, function (data) {
        if (data.includes('登录成功')) {
            alert(data + '欢迎回来！')
            window.location = "../indexAdmin.html";
        } else {
            alert(data);
        }
    })
}

function showUserList() {
    const _this = this;
    $.get('http://localhost:8080/admin/admin-get-User',
        function (data) {
            _this.tableData = data;
            const item = {
                name: data.name,
            };
            return {
                tableData: Array(data.length).fill(item)
            }
        })
}

function checkRepeatPassword() {
    if ($("#password").val() !== $("#rePassword").val()) {
        alert("两次输入的密码不一致，请重新输入");
    }
}

function logOut() {
    alert("已退出");
    window.location = "http://localhost:8080/login.html";
}

function showDataAndName() {
    $.get('http://localhost:8080/api/page/index-Show-DataAndName',
        function (data) {
            if (data.name === null) {
                alert('似乎没有登录？请先登录')
                window.location = "http://localhost:8080/login.html";
            } else {
                document.getElementById("username").innerHTML = data.name;
                document.getElementById("name").innerHTML = data.name;
                let brands = data.data;
                let tableData = ""
                for (let i = 0; i < brands.length; i++) {
                    let brand = brands[i];
                    tableData += "     <tr>\n" +
                        "                        <th scope=\"row\"><b>" + brand.communicate + "</b></th>\n" +
                        "                        <td><b>" + brand.name + "</b></td>\n" +
                        "                        <td><b>" + brand.sex + "</b></td>\n" +
                        "                        <td>\n" +
                        "                            <div className=\"tm-status-circle moving\">\n" +
                        "                            </div>\n" +
                        "                            " + brand.status + "\n" +
                        "                        </td>\n" +
                        "                        <td><b>赤坎区南桥北路</b></td>\n" +
                        "                        <td>11:00 ，2022.5.6</td>\n" +
                        "                        <td>" + brand.contact + "</td>\n" +
                        "                    </tr>"

                }
                let tmp = document.createElement('tr');//创建一个div
                tmp.innerHTML = tableData
                document.getElementById("oldManInfoTable").appendChild(tmp);
            }
        })
}

function getOldManNotificationInfo() {
    $.get('http://localhost:8080/api/page/index-Show-Notification',
        function (data) {

        })
}
