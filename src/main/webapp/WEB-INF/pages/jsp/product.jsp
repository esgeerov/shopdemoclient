<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        table {
            color: darkblue;
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .tooltip {
            position: relative;
            display: inline-block;
            cursor: pointer;
        }

        .tooltip .tooltiptext {
            visibility: hidden;
            width: 200px;
            background-color: #555;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px;
            position: absolute;
            z-index: 1;
            bottom: 100%;
            left: 50%;
            margin-left: -100px;
            opacity: 0;
            transition: opacity 0.3s;
        }

        .tooltip:hover .tooltiptext {
            visibility: visible;
            opacity: 1;
        }

        #searchInput {
            width: 100%;
            padding: 12px;
            margin: 20px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${not empty msg}">
        <h1 style="color: red">${msg}</h1>
    </c:when>
    <c:otherwise>
        <h1>Products</h1>
        <input type="text" id="searchInput" onkeyup="filterTable()" placeholder="Search for names..">
        <table id="productTable">
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Price</th>
                <th>Currency</th>
                <th>Category</th>
            </tr>
            <c:forEach items="${result}" var="r">
                <tr>
                    <td>${r.productId}</td>
                    <td class="tooltip">${r.name}
                        <span class="tooltiptext">
                        Product ID: ${r.productId}<br>
                        Price: ${r.price} ${r.currency}<br>
                        Category: ${r.respCategory.name}
                    </span>
                    </td>
                    <td>${r.price}</td>
                    <td>${r.currency}</td>
                    <td>${r.respCategory.name}</td>
                </tr>
            </c:forEach>
        </table>
        <script>
            function filterTable() {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("searchInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("productTable");
                tr = table.getElementsByTagName("tr");
                for (i = 1; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[1];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
        </script>
        </body>
        </html>

    </c:otherwise>
</c:choose>
