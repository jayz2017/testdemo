<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	/* function formatAction(val, row) {
		if (row.application_status == '待审批') {
			return "<a href='javascript:startApply("+row.application_id+")'>提交申请</a>";
		} else {
			return "<a href='javascript:openListCommentDialog("
					+ row.processInstanceId + ")'>查看历史批注</a>";
		}
	} */

	function openListCommentDialog(processInstanceId) {
		$("#dg2").datagrid("load", {
			processInstanceId : processInstanceId
		});
		$("#dlg2").dialog("open").dialog("setTitle", "查看历史批注");
	}

	function openLeaveAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加请假单信息");
	}
	function openLeaveAddDialog1() {
		$("#dlg3").dialog("open").dialog("setTitle", "添加申请单信息");
	}
	function saveLeave() {
		$("#fm")
				.form(
						"submit",
						{
							url : '${pageContext.request.contextPath}/Application/insert1.action',
							onSubmit : function() {
								return $(this).form("validate");
							},
							success : function(result) {
								var result = eval('(' + result + ')');
								if (result) {
									$.messager.alert("系统系统", "保存成功！");
									resetValue();
									$("#dlg").dialog("close");
									$("#dg").datagrid("reload");
								} else {
									$.messager.alert("系统系统", "保存失败！");
									return;
								}
							}
						});
	}
	function saveLeave1() {
		$("#fm1")
				.form(
						"submit",
						{
							url : '${pageContext.request.contextPath}/Application/insert1.action',
							onSubmit : function() {
								return $(this).form("validate");
							},
							success : function(result) {
								var result = eval('(' + result + ')');
								if (result) {
									$.messager.alert("系统系统", "保存成功！");
									resetValue();
									$("#dlg3").dialog("close");
									$("#dg").datagrid("reload");
								} else {
									$.messager.alert("系统系统", "保存失败！");
									return;
								}
							}
						});
	}
	function resetValue() {
		$("#leaveDays").val("");
		$("#leaveReason").val("");
	}

	function closeLeaveDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	function closeLeaveDialog1() {
		$("#dlg3").dialog("close");
		resetValue();
	}
	//提交请假流程申请
	function startApply(applicationId){
		alert(applicationId);
		/* $.post("${pageContext.request.contextPath}/leave/startApply.action",{"applicationId":applicationId},function(result){
			if(result.success){
				$.messager.alert("系统系统","请假申请提交成功，目前审核中，请耐心等待！");
				$("#dg").datagrid("reload");
			}else{
				$.messager.alert("系统系统","请假申请提交失败，请联系管理员！");
			}
		},"json"); */
	}
</script>
</head>
<body style="margin: 1px">
	<table id="dg" title="流程信息管理" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true"
		url="${pageContext.request.contextPath}/Application/selectOne.action"
		fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="profile_id" width="30" align="center">流程信息主键</th>
				<th field="application_status" width="80" align="center">流程信息状态</th>
				<th field="aplication_cretor" width="30" align="center">流程信息申请人</th>
				<th field="current_operator" width="200" align="center">流程信息申请人</th>
				<th field="submit_time" width="30" align="center">流程提交时间</th>
				<th field="lastMdfyOperator" width="30" hidden="true" align="center">上一个修改人</th>
				<th field="lastMdfyTime" width="30" hidden="true" align="center">上一个修改时间</th>
				<th field="priority" width="30" hidden="true" align="center">优先级</th>
				<th field="product_type" width="30" hidden="true" align="center">产品类型</th>
				<th field="application_id" width="30" hidden="true" align="center"
					type="hidden">产品ID</th>
				<th field="work_id" hidden="false" type="hidden" />
				<!-- <th field="action" width="50" align="center"
					formatter="formatAction">操作</th>
			</tr> -->
		</thead>
	</table>
	<div id="tb">
		<div>
			<a href="javascript:openLeaveAddDialog()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">新增申请单</a>
		</div>
		<!-- <div>
			<a href="javascript:openLeaveAddDialog1()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">新增申请单</a>
		</div> -->
	</div>
  
	<div id="dlg" class="easyui-dialog"
		style="width: 620px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">

		<form id="fm" method="post">
			<table cellpadding="8px">
				<tr>
					<td>申请天数：</td>
					<td><input type="text" id="priority" name="priority"
						class="easyui-numberbox" required="true" /></td>
				</tr>
				<tr>
					<td valign="top">申假原因：</td>
					<td><input type="hidden" name="state" value="未提交" /> <textarea
							type="text" id="product_type" name="product_type" rows="5"
							cols="49" class="easyui-validatebox" required="true"></textarea>
					</td>
					<td><input type="hidden" name="lastMdfyOperator" value="李小龙" />
						<input type="hidden" name="current_operator"
						value=${currentMemberShip.staff_name } /> <input type="hidden"
						name="aplication_cretor" value="理查德" /> <input type="hidden"
						name="work_id" value="testProcess" /></td>
				</tr>
			</table>
		</form>

	</div>

	<div id="dlg-buttons">
		<a href="javascript:saveLeave()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeLeaveDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>


	<div id="dlg2" class="easyui-dialog"
		style="width: 750px; height: 250px; padding: 10px 20px" closed="true">

		<table id="dg2" title="批注列表" class="easyui-datagrid" fitColumns="true"
			url="${pageContext.request.contextPath}/task/listHistoryCommentWithProcessInstanceId.action"
			style="width: 700px; height: 200px;">
			<thead>
				<tr>
					<th field="time" width="120" align="center">批注时间</th>
					<th field="userId" width="100" align="center">批注人</th>
					<th field="message" width="200" align="center">批注信息</th>
				</tr>
			</thead>
		</table>

	</div>
<%-- <div id="dlg3" class="easyui-dialog"
		style="width: 620px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">

		<form id="fm1" method="post">
			<table cellpadding="8px">
				<tr>
					<td>申请天数：</td>
					<td><input type="text" id="priority" name="priority"
						class="easyui-numberbox" required="true" /></td>
				</tr>
				<tr>
					<td valign="top">申请原因：</td>
					<td><input type="hidden" name="state" value="未提交" /> <textarea
							type="text" id="product_type" name="product_type" rows="5"
							cols="49" class="easyui-validatebox" required="true"></textarea>
					</td>
					<td><input type="hidden" name="lastMdfyOperator" value="李小龙" />
						<input type="hidden" name="current_operator"
						value=${currentMemberShip.staff_name } /> <input type="hidden"
						name="aplication_cretor" value="理查德" /> <input type="hidden"
						name="work_id" value="test111" /></td>
				</tr>
			</table>
		</form>

	</div>
<div id="dlg-buttons">
		<a href="javascript:saveLeave1()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeLeaveDialog1()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div> --%>
</body>
</html>