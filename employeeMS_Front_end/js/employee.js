function saveEmployee(){
    let id=$('#exampleFormControlInput1').val();
    let name=$('#exampleFormControlInput2').val();
    let address=$('#exampleFormControlInput3').val();
    let empNum=$('#exampleFormControlInput3').val();
    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/save",
        async:true,
        data: JSON.stringify({
            "id": id,
            "empName": name,
            "empAddress": address,
            "empNumber": empNum
        }),
        success: function (data) {
            alert("Employee Saved");
            getAllEmployees();
        },
        error:function(xhr,exception){
            alert("error");
        }
    });
}

function updateEmployee(){
    let id=$('#exampleFormControlInput1').val();
    let name=$('#exampleFormControlInput2').val();
    let address=$('#exampleFormControlInput3').val();
    let empNum=$('#exampleFormControlInput4').val();
    $.ajax({
        method: "PUT",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/employee/update",
        async:true,
        data: JSON.stringify({
            "id": id,
            "empName": name,
            "empAddress": address,
            "empNumber": empNum
        }),
        success: function (data) {
            alert("Employee Details Updated");
            getAllEmployees();
        },
        error:function(xhr,exception){
            alert("error");
        }
    });
}

function getAllEmployees(){
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/employee/getAll",
        async:true,
        success: function (data) {
            if(data.code==="00"){
                $('#empTable').empty();
                for(let emp of data.content){
                    let emID=emp.id;
                    let name=emp.empName;
                    let address=emp.empAddress;
                    let number=emp.empNumber;

                    var row = `<tr><td>${emID}</td><td>${name}</td><td>${address}</td><td>${number}</td></tr>`;
                    $('#empTable').append(row);
                }
            }
        },
        error:function(xhr,exception){
            alert("error");
        }
    });
}

function deleteEmployee(){
    let id=$('#exampleFormControlInput1').val();
    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/api/v1/employee/delete/"+id,
        async:true,
        success: function (data) {
            alert("Employee Details Deleted");
            getAllEmployees();
        },
        error:function(xhr,exception){
            alert("error");
        }
    });
}

getAllEmployees();