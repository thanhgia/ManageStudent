<%-- 
    Document   : manage
    Created on : Jul 5, 2019, 9:45:44 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage student</title>
    </head>
    <style>
        #tableResult{
            max-height: 200px;
            overflow-y: scroll;

        }
        #tableView input{
            width: 100%;
        }
        #tableView {
            width: 100%;
        }
        #search {
            width: 250px;
        }
        .button {
            width: 125px;
        }
        #addr{
            width: 470px;
        }
        #phone{
            width: 270px;
        }
        #create{
            width: 370px;
        }
        #total {
            padding-left: 50px;
        }
        #update{
            padding-left: 68%;
        }
    </style>
    <body>
        <c:if test="${not empty sessionScope.USERNAME_STAFF}">
            <font color="red">
            Welcome, ${sessionScope.USERNAME_STAFF}
            </font>  
            
            <a href="logout" style="float: right;">Logout</a>

            <h1>Manage Students</h1>
            <form action="SearchStudent">

                <table>
                    <thead>
                        <tr>
                            <td rowspan="2" width="200px">Status, Id or Name<br/>
                                (Approximate name)
                            </td>
                            <td  colspan="2" width="250px">
                                <input type="text" name="txtSearch" value="${param.txtSearch}" id="search"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="Search" name="btAction" class="button"/>
                            </td>

                            </form>

                            <td>
                                <form action="ShowAllStudent" method="POST">
                                    <input type="submit" value="Show All" name="btAction" class="button"/>

                                </form>
                            </td>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table><br/>




                <c:set var="result" value="${requestScope.RESULT}"/>
                <c:set var="numberStudent" value="${requestScope.NUMBERSTUDENT}"/>
                <c:set var="errorUpdate" value="${requestScope.ERRORUPDATE}"/>

                <c:if test="${not empty result}">

                    <div id="title"> List of students <b id="total">Total of search result:${numberStudent}</b>
                        <c:if test="${not empty requestScope.RESULTUPDATE}" >
                            <font id="update" color="green">${requestScope.RESULTUPDATE}</font>
                        </c:if>
                    </div>

                    <c:if test="${not empty errorUpdate}">
                        <div>
                            <c:if test="${not empty requestScope.ERRORINID}">
                                <font color="red">Error in: ${requestScope.ERRORINID}</font> 
                            </c:if>
                            <c:if test="${not empty errorUpdate.classLengthError}">
                                | <font color="red">${errorUpdate.classLengthError}</font> 
                            </c:if>
                            <c:if test="${not empty errorUpdate.address1LengthError}">
                                | <font color="red">${errorUpdate.address1LengthError}</font>
                            </c:if>
                            <c:if test="${not empty errorUpdate.phoneLengthError}">
                                | <font color="red">${errorUpdate.phoneLengthError}</font>
                            </c:if>
                            <c:if test="${not empty errorUpdate.phoneWrongFormat}">
                                | <font color="red">${errorUpdate.phoneWrongFormat}</font>
                            </c:if>
                        </div>
                    </c:if>
                    <div id="tableResult">
                        <table border="1" id="tableView">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Id</th>
                                    <th>Full name</th>
                                    <th>Class</th>
                                    <th>Address1</th>
                                    <th>Address2</th>
                                    <th>Status</th>
                                    <th>Phone</th>
                                    <th>Update</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${result}" varStatus="counter">
                                <form action="UpdateStudent" method="POST">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                            .
                                        </td>
                                        <td>
                                            ${dto.id}
                                            <input type="hidden" name="txtId" value="${dto.id}" />
                                        </td>
                                        <td>
                                            ${dto.firstName} ${dto.middleName} ${dto.lastName}
                                        </td>
                                        <td>

                                            <input type="text" name="txtClass" value="${dto.classes}" />
                                        </td>
                                        <td>

                                            <input type="text" name="txtAddress1" value="${dto.address1}" />
                                        </td>
                                        <td>

                                            <input type="text" name="txtAddress2" value="${dto.address2}" />
                                        </td>
                                        <td>    

                                            <select name="cbStatus" style="width: 100%;">

                                                <option value="0" <c:if test="${dto.status == 0}">selected="selected"</c:if>>Studying</option>                          
                                                <option value="1" <c:if test="${dto.status == 1}">selected="selected"</c:if>>Break</option>                                       
                                                <option value="2" <c:if test="${dto.status == 2}">selected="selected"</c:if>>Dropout</option>

                                                </select>

                                            </td>
                                            <td>

                                                <input type="text" name="txtPhone" value="${dto.phone}" />
                                        </td>
                                        <td>
                                            <input type="submit" value="Update" />
                                            <input type="hidden" name="txtLastSearchValue" value="${param.txtSearch}" />
                                        </td>
                                    </tr>

                                </form>
                            </c:forEach>


                            </tbody>
                        </table>

                    </div>
                </c:if>

                <c:set var="searchValue" value="${fn:trim(param.txtSearch)}"/>
                <c:if test="${empty result and not empty searchValue}">
                    <h1>No record is matched!!!</h1>
                </c:if>

                <br/>
                <p><b>Insert New Student</b></p>
                <c:set var="error" value="${requestScope.ERROR}"/>
                <form action="CreateNewStudent" method="POST">
                    <table>
                        <thead>
                            <!--/input id -->
                            <tr>
                                <td>Id</td>
                                <td>
                                    <input type="text" name="txtId" value="" />
                                </td>

                            </tr>
                            <!--/ Check error-->

                            <c:if test="${not empty error.idLengthError}">  
                            <td colspan="5">
                                <font color="red">${error.idLengthError}</font>
                            </td>
                        </c:if>
                        <c:if test="${not empty error.idIsExist}">  
                            <td colspan="5">
                                <font color="red">${error.idIsExist}</font>
                            </td>
                        </c:if>

                        <!--/input first,middle,last name -->
                        <tr>

                            <td>First Name</td>
                            <td>
                                <input type="text" name="txtFirstName" value="" />
                            </td>

                            <td>Middle Name</td>
                            <td>
                                <input type="text" name="txtMiddleName" value="" />
                            </td>

                            <td>Last Name</td>
                            <td>
                                <input type="text" name="txtLastName" value="" />
                            </td>
                        </tr>
                        <!--/ Check error-->
                        <td colspan="2">
                        <c:if test="${not empty error.firstNameLengthError}">
                                <font color="red" size="2"> ${error.firstNameLengthError}</font>                            
                        </c:if>
                                </td>
                        <td colspan="2">
                            <c:if test="${not empty error.middleNameLengthError}">
                                <font color="red" size="2"> ${error.middleNameLengthError}</font>
                            </c:if>
                        </td>
                        <td colspan="2">
                            <c:if test="${not empty error.lastNameLengthError}">
                                | <font color="red" size="2"> ${error.lastNameLengthError}</font>
                            </c:if>
                        </td>
                        
                        <tr>
                            <td>Male</td>
                            <td>
                                <input type="checkbox" name="chkSex" value="" />
                            </td>
                        </tr>
                        <!--/input address 1 -->
                        <tr>
                            <td>Address1</td>
                            <td colspan="4">
                                <input type="text" name="txtAddress1" value="" id="addr"/>
                            </td>
                        </tr>
                        <!--/ Check error-->
                        <c:if test="${not empty error.address1LengthError}">
                            <td colspan="5">
                                <font color="red"> ${error.address1LengthError}</font>
                            </td>
                        </c:if>
                        <!--/input address 2 -->
                        <tr>
                            <td>Address2</td>
                            <td colspan="4">
                                <input type="text" name="txtAddress2" value="" id="addr"/>
                            </td>
                        </tr>
                        <!--/input phone -->
                        <tr>
                            <td>Phone</td>
                            <td colspan="2">
                                <input type="text" name="txtPhone" value="" id="phone"/>
                            </td>
                        </tr>
                        <!--/ Check error-->
                        <c:if test="${not empty error.phoneLengthError}">
                            <td colspan="2">
                                <font color="red"> ${error.phoneLengthError}</font>
                            </td>
                        </c:if>
                        <c:if test="${not empty error.phoneIsNotNumber}">
                            <td colspan="2">
                                <font color="red"> ${error.phoneIsNotNumber}</font>
                            </td>
                        </c:if>
                        <c:if test="${not empty error.phoneWrongFormat}">
                            <td colspan="2">
                                <font color="red"> ${error.phoneWrongFormat}</font>
                            </td>
                        </c:if>
                        <!--/input Class -->
                        <tr>
                            <td>Class</td>
                            <td colspan="1">
                                <input type="text" name="txtClass" value="" />
                            </td>
                        </tr>
                        <!--/ Check error-->
                        <c:if test="${not empty error.classLengthError}">
                            <td colspan="2">
                                <font color="red"> ${error.classLengthError}</font>
                            </td>
                        </c:if>
                        <tr>
                            <td colspan="3">
                                <input type="submit" value="Create New Student" id="create"/>
                                <input type="hidden" name="button" value="${param.btAction}" />
                            </td>
                            <td>
                                <c:set var="checkInsert" value="${requestScope.INSERTSUCCESS}"/>
                                <c:if test="${not empty checkInsert}">
                                    <font color="green">${checkInsert}</font>
                                </c:if>
                            </td>

                        </tr>
                        </thead>
                    </table>
                </form>
            </c:if>
            <c:if test="${empty sessionScope.USERNAME_STAFF}">
                <jsp:forward page="login.html"/>
            </c:if>
    </body>
</html>
