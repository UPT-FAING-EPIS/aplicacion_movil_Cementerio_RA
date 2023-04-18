document.addEventListener("DOMContentLoaded", function () {
    $('#tbl').DataTable();
    $(".confirmar").submit(function (e) {
        e.preventDefault();
        Swal.fire({
            title: 'Esta seguro de eliminar?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'SI, Eliminar!'
        }).then((result) => {
            if (result.isConfirmed) {
                this.submit();
            }
        })
    })
    $("#nom_cliente").autocomplete({
        minLength: 3,
        source: function (request, response) {
            $.ajax({
                url: "ajax.php",
                dataType: "json",
                data: {
                    q: request.term
                },
                success: function (data) {
                    response(data);
                }
            });
        },
        select: function (event, ui) {
            $("#idcliente").val(ui.item.id);
            $("#nom_cliente").val(ui.item.label);
            $("#tel_cliente").val(ui.item.telefono);
            $("#dir_cliente").val(ui.item.direccion);
        }
    })
    $("#producto").autocomplete({
        minLength: 3,
        source: function (request, response) {
            $.ajax({
                url: "ajax.php",
                dataType: "json",
                data: {
                    pro: request.term
                },
                success: function (data) {
                    response(data);
                }
            });
        },
        select: function (event, ui) {
            $("#producto").val(ui.item.value);
            setTimeout(
                function () {
                    e = jQuery.Event("keypress");
                    e.which = 13;
                    registrarDetalle(e, ui.item.id, 1, ui.item.precio);
                }
            )
        }
    })
    $('#btn_generar').click(function (e) {
        e.preventDefault();
        var rows = $('#tblDetalle tr').length;
        if (rows > 2) {
            var action = 'procesarVenta';
            var id = $('#idcliente').val();
            $.ajax({
                url: 'ajax.php',
                async: true,
                data: {
                    procesarVenta: action,
                    id: id
                },
                success: function (response) {
                    const res = JSON.parse(response);
                    if (response != 'error') {
                        Swal.fire({
                            position: 'top-end',
                            icon: 'success',
                            title: 'Venta Generada',
                            showConfirmButton: false,
                            timer: 2000
                        })
                        setTimeout(() => {
                            generarPDF(res.id_cliente, res.id_venta);
                            location.reload();
                        }, 300);
                    } else {
                        Swal.fire({
                            position: 'top-end',
                            icon: 'error',
                            title: 'Error al generar la venta',
                            showConfirmButton: false,
                            timer: 2000
                        })
                    }
                },
                error: function (error) {

                }
            });
        }else{
            Swal.fire({
                position: 'top-end',
                icon: 'warning',
                title: 'No hay producto para generar la venta',
                showConfirmButton: false,
                timer: 2000
            })
        }
    });
    if (document.getElementById("detalle_venta")) {
        listar();
    }
})

function listar() {
    let html = '';
    let detalle = 'detalle';
    $.ajax({
        url: "ajax.php",
        dataType: "json",
        data: {
            detalle: detalle
        },
        success: function (response){
            response.forEach(row => {
                html += `<tr>
                <td>${row['id']}</td>
                <td>${row['descripcion']}</td>
                <td>${row['cantidad']}</td>
                <td>${row['precio_venta']}</td>
                <td>${row['sub_total']}</td>
                <td><button class="btn btn-danger" type="button" onclick="deleteDetalle(${row['id']})">
                <i class="fas fa-trash-alt"></i></button></td>
                </tr>`;
            });
            document.querySelector("#detalle_venta").innerHTML = html;
            calcular();
            
        }
    });
}

function registrarDetalle(e, id, cant, precio) {
    if (document.getElementById('producto').value != '') {
        if (e.which == 13) {
            if (id != null) {
                let action = 'regDetalle';
                $.ajax({
                    url: "ajax.php",
                    type: 'POST',
                    dataType: "json",
                    data: {
                        id: id,
                        cant: cant,
                        action: action,
                        precio: precio
                    },
                    success: function (response) {
                        if (response == 'registrado') {
                            Swal.fire({
                                position: 'top-end',
                                icon: 'success',
                                title: 'Producto Ingresado',
                                showConfirmButton: false,
                                timer: 2000
                            })
                            document.querySelector("#producto").value = '';
                            document.querySelector("#producto").focus();
                            listar();
                        } else if (response == 'actualizado') {
                            Swal.fire({
                                position: 'top-end',
                                icon: 'success',
                                title: 'Producto Actualizado',
                                showConfirmButton: false,
                                timer: 2000
                            })
                            document.querySelector("#producto").value = '';
                            document.querySelector("#producto").focus();
                            listar();
                        } else {
                            Swal.fire({
                                position: 'top-end',
                                icon: 'error',
                                title: 'Error al ingresar el producto',
                                showConfirmButton: false,
                                timer: 2000
                            })
                        }
                    }
                });
            }
        }
    }
}
function deleteDetalle(id) {
    let detalle = 'Eliminar'
    $.ajax({
        url: "ajax.php",
        data: {
            id: id,
            delete_detalle: detalle
        },
        success: function (response) {
            console.log(response);
            if (response == 'restado') {
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Producto Descontado',
                    showConfirmButton: false,
                    timer: 2000
                })
                document.querySelector("#producto").value = '';
                document.querySelector("#producto").focus();
                listar();
            } else if (response == 'ok') {
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Producto Eliminado',
                    showConfirmButton: false,
                    timer: 2000
                })
                document.querySelector("#producto").value = '';
                document.querySelector("#producto").focus();
                listar();
            } else {
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: 'Error al eliminar el producto',
                    showConfirmButton: false,
                    timer: 2000
                })
            }
        }
    });
}
function calcular() {
    // obtenemos todas las filas del tbody
    var filas = document.querySelectorAll("#tblDetalle tbody tr");

    var total = 0;

    // recorremos cada una de las filas
    filas.forEach(function (e) {

        // obtenemos las columnas de cada fila
        var columnas = e.querySelectorAll("td");

        // obtenemos los valores de la cantidad y importe
        var importe = parseFloat(columnas[4].textContent);

        total += importe;
    });

    // mostramos la suma total
    var filas = document.querySelectorAll("#tblDetalle tfoot tr td");
    filas[1].textContent = total.toFixed(2);
}
function generarPDF(cliente, id_venta) {
    url = 'pdf/generar.php?cl=' + cliente + '&v=' + id_venta;
    window.open(url, '_blank');
}
if (document.getElementById("sales-chart")) {
    const action = "sales";
    $.ajax({
        url: 'chart.php',
        type: 'POST',
        data: {
            action
        },
        async: true,
        success: function (response) {
            if (response != 0) {
                var data = JSON.parse(response);
                var nombre = [];
                var cantidad = [];
                for (var i = 0; i < data.length; i++) {
                    nombre.push(data[i]['descripcion']);
                    cantidad.push(data[i]['existencia']);
                }
                try {
                    //Sales chart
                    var ctx = document.getElementById("sales-chart");
                    if (ctx) {
                        ctx.height = 150;
                        var myChart = new Chart(ctx, {
                            type: 'line',
                            data: {
                                labels: nombre,
                                type: 'line',
                                defaultFontFamily: 'Poppins',
                                datasets: [{
                                    label: "Disponible",
                                    data: cantidad,
                                    backgroundColor: 'transparent',
                                    borderColor: 'rgba(220,53,69,0.75)',
                                    borderWidth: 3,
                                    pointStyle: 'circle',
                                    pointRadius: 5,
                                    pointBorderColor: 'transparent',
                                    pointBackgroundColor: 'rgba(220,53,69,0.75)',
                                }, {
                                    label: "Cantidad",
                                    data: [0, 2, 3, 4, 5, 6, 7, 8, 9, 10],
                                    backgroundColor: 'transparent',
                                    borderColor: 'rgba(40,167,69,0.75)',
                                    borderWidth: 3,
                                    pointStyle: 'circle',
                                    pointRadius: 5,
                                    pointBorderColor: 'transparent',
                                    pointBackgroundColor: 'rgba(40,167,69,0.75)',
                                }]
                            },
                            options: {
                                responsive: true,
                                tooltips: {
                                    mode: 'index',
                                    titleFontSize: 12,
                                    titleFontColor: '#000',
                                    bodyFontColor: '#000',
                                    backgroundColor: '#fff',
                                    titleFontFamily: 'Poppins',
                                    bodyFontFamily: 'Poppins',
                                    cornerRadius: 3,
                                    intersect: false,
                                },
                                legend: {
                                    display: false,
                                    labels: {
                                        usePointStyle: true,
                                        fontFamily: 'Poppins',
                                    },
                                },
                                scales: {
                                    xAxes: [{
                                        display: true,
                                        gridLines: {
                                            display: false,
                                            drawBorder: false
                                        },
                                        scaleLabel: {
                                            display: false,
                                            labelString: 'Month'
                                        },
                                        ticks: {
                                            fontFamily: "Poppins"
                                        }
                                    }],
                                    yAxes: [{
                                        display: true,
                                        gridLines: {
                                            display: false,
                                            drawBorder: false
                                        },
                                        scaleLabel: {
                                            display: true,
                                            labelString: 'Cantidad',
                                            fontFamily: "Poppins"

                                        },
                                        ticks: {
                                            fontFamily: "Poppins"
                                        }
                                    }]
                                },
                                title: {
                                    display: false,
                                    text: 'Normal Legend'
                                }
                            }
                        });
                    }
                } catch (error) {
                    console.log(error);
                }
            }
        },
        error: function (error) {
            console.log(error);
        }
    });
}
if (document.getElementById("polarChart")) {
    const action = "polarChart";
    $('.alertAddProduct').html('');
    $.ajax({
        url: 'chart.php',
        type: 'POST',
        async: true,
        data: {
            action
        },
        success: function (response) {
            if (response != 0) {
                var data = JSON.parse(response);
                var nombre = [];
                var cantidad = [];
                for (var i = 0; i < data.length; i++) {
                    nombre.push(data[i]['descripcion']);
                    cantidad.push(data[i]['cantidad']);
                }
            }
            try {

                // polar chart
                var ctx = document.getElementById("polarChart");
                if (ctx) {
                    ctx.height = 200;
                    var myChart = new Chart(ctx, {
                        type: 'polarArea',
                        data: {
                            datasets: [{
                                data: cantidad,
                                backgroundColor: [
                                    "rgb(0, 123, 255)",
                                    "rgb(255, 0, 0)",
                                    "rgb(0, 255, 0)",
                                    "rgb(0,0,0)",
                                    "rgb(0, 0, 255)"
                                ]

                            }],
                            labels: nombre
                        },
                        options: {
                            legend: {
                                position: 'top',
                                labels: {
                                    fontFamily: 'Poppins'
                                }

                            },
                            responsive: true
                        }
                    });
                }

            } catch (error) {
                console.log(error);
            }
        },
        error: function (error) {
            console.log(error);

        }
    });
}
function btnCambiar(e) {
    e.preventDefault();
    const actual = document.getElementById('actual').value;
    const nueva = document.getElementById('nueva').value;
    if (actual == "" || nueva == "") {
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: 'Los campos estan vacios',
            showConfirmButton: false,
            timer: 2000
        })
    } else {
        const cambio = 'pass';
         $.ajax({
             url: "ajax.php",
             type: 'POST',
             data: {
                 actual: actual,
                 nueva: nueva,
                 cambio: cambio
             },
             success: function (response) {
                 console.log(response);
                 if (response == 'ok') {
                     Swal.fire({
                         position: 'top-end',
                         icon: 'success',
                         title: 'Contraseña modificado',
                         showConfirmButton: false,
                         timer: 2000
                     })
                     document.querySelector('frmPass').reset();
                     $("#nuevo_pass").modal("hide");
                 } else if (response == 'dif') {
                     Swal.fire({
                         position: 'top-end',
                         icon: 'error',
                         title: 'La contraseña actual incorrecta',
                         showConfirmButton: false,
                         timer: 2000
                     })
                 } else {
                     Swal.fire({
                         position: 'top-end',
                         icon: 'error',
                         title: 'Error al modificar la contraseña',
                         showConfirmButton: false,
                         timer: 2000
                     })
                 }
             }
         });
    }
}