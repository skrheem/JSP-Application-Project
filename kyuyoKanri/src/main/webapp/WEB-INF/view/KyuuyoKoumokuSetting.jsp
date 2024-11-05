<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<!-- 김찬호 金燦鎬 -->
<!-- 급여항목설정 給与項目設定 -->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>給与項目設定</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/KyuuyoKoumokuSetting.css">
<script
	src="${pageContext.request.contextPath}/js/KyuuyoKoumokuSetting.js"></script>

</head>
<body>
	<div class="container">
		<h1>給与項目設定</h1>

		<div class="flex-container">
			<!-- 支給項目 設定 -->
			<div class="table-section">
				<h2>支給項目 設定</h2>
				<table>
					<thead>
						<tr>
							<th>支給項目</th>
							<th>課税有無</th>
							<th>非課税限度額</th>
							<th>計算タイプ</th>
							<th>勤怠リンク/日割り支給</th>
							<th>使用有無</th>
						</tr>
					</thead>
					<tbody id="payItemTable">
						<tr data-name="基本給" data-taxable="課税" data-nonTaxLimit="なし"
							data-calculationType="一括支給" data-attendanceLink="200000"
							data-usage="使用" onclick="fillPayItemForm(this)">
							<td>基本給</td>
							<td>全課税</td>
							<td>なし</td>
							<td>一括支給</td>
							<td>200,000</td>
							<td><input type="checkbox" checked></td>
						</tr>
						<tr data-name="食費" data-taxable="非課税" data-nonTaxLimit="200000"
							data-calculationType="一括支給" data-attendanceLink="200000"
							data-usage="使用" onclick="fillPayItemForm(this)">
							<td>食費</td>
							<td>非課税</td>
							<td>200,000</td>
							<td>一括支給</td>
							<td>200,000</td>
							<td><input type="checkbox" checked></td>
						</tr>
						<tr data-name="育児手当" data-taxable="課税" data-nonTaxLimit="なし"
							data-calculationType="一括支給" data-attendanceLink="なし"
							data-usage="使用" onclick="fillPayItemForm(this)">
							<td>育児手当</td>
							<td>全課税</td>
							<td>なし</td>
							<td>一括支給</td>
							<td>なし</td>
							<td><input type="checkbox" checked></td>
						</tr>
						<tr data-name="車両維持費" data-taxable="非課税" data-nonTaxLimit="200000"
							data-calculationType="一括支給" data-attendanceLink="200000"
							data-usage="使用" onclick="fillPayItemForm(this)">
							<td>車両維持費</td>
							<td>非課税</td>
							<td>200,000</td>
							<td>一括支給</td>
							<td>200,000</td>
							<td><input type="checkbox" checked></td>
						</tr>
						<tr data-name="勤続手当" data-taxable="課税" data-nonTaxLimit="なし"
							data-calculationType="一括支給" data-attendanceLink="延長勤務"
							data-usage="使用" onclick="fillPayItemForm(this)">
							<td>勤続手当</td>
							<td>全課税</td>
							<td>なし</td>
							<td>一括支給</td>
							<td>延長勤務</td>
							<td><input type="checkbox" checked></td>
						</tr>
						<tr data-name="当直手当" data-taxable="課税" data-nonTaxLimit="なし"
							data-calculationType="一括支給" data-attendanceLink="延長勤務"
							data-usage="使用" onclick="fillPayItemForm(this)">
							<td>当直手当</td>
							<td>全課税</td>
							<td>なし</td>
							<td>一括支給</td>
							<td>延長勤務</td>
							<td><input type="checkbox" checked></td>
						</tr>
						<tr data-name="賞与" data-taxable="課税" data-nonTaxLimit="なし"
							data-calculationType="一括支給" data-attendanceLink="休日勤務"
							data-usage="使用" onclick="fillPayItemForm(this)">
							<td>賞与</td>
							<td>全課税</td>
							<td>なし</td>
							<td>一括支給</td>
							<td>休日勤務</td>
							<td><input type="checkbox" checked></td>
						</tr>
						<tr data-name="休日手当" data-taxable="課税" data-nonTaxLimit="なし"
							data-calculationType="一括支給" data-attendanceLink="休日勤務"
							data-usage="使用" onclick="fillPayItemForm(this)">
							<td>休日手当</td>
							<td>全課税</td>
							<td>なし</td>
							<td>一括支給</td>
							<td>休日勤務</td>
							<td><input type="checkbox" checked></td>
						</tr>
					</tbody>

				</table>
			</div>

			<!-- 支給項目 入力 -->
			<div class="form-section">
				<div style="height: 40px;"></div> <!-- 원하는 높이만큼 공간 확보 -->

				<label for="payItemName">支給項目</label> <input type="text"
					id="payItemName" placeholder="支給項目を入力してください"> <label
					for="taxable">課税有無</label> <select id="taxable">
					<option value="課税">課税</option>
					<option value="非課税">非課税</option>
				</select> <label for="nonTaxLimit">非課税限度額</label> <input type="number"
					id="nonTaxLimit" placeholder="非課税限度額を入力してください"> <label
					for="calculationType">計算タイプ</label> <select id="calculationType">
					<option value="定数">定数</option>
					<option value="変数">変数</option>
				</select> <label for="attendanceLink">勤怠リンク/日割り支給</label> <input type="text"
					id="attendanceLink" placeholder="勤怠リンク情報を入力してください"> <label>使用有無</label>
				<input type="radio" name="payItemUsage" value="使用" checked>
				使用 <input type="radio" name="payItemUsage" value="使用しない">
				使用しない

				<div class="form-buttons">
					<button class="btn btn-add">追加</button>
					<button class="btn btn-update">修正</button>
					<button class="btn btn-delete">削除</button>
					<button class="btn btn-clear">内容を消去</button>
				</div>
			</div>
		</div>

		<div class="flex-container">
			<!-- 控除項目 設定 -->
			<div class="table-section">
				<h2>控除項目 設定</h2>
				<table>
					<thead>
						<tr>
							<th>控除項目</th>
							<th>計算タイプ</th>
							<th>使用有無</th>
							<th>備考</th>
						</tr>
					</thead>
					<!-- 控除項目 テーブル -->
					<tbody id="deductionItemTable">
						<tr data-name="国民年金" data-calculationType="なし" data-usage="使用"
							onclick="fillDeductionItemForm(this)">
							<td>国民年金</td>
							<td>なし</td>
							<td><input type="checkbox" checked></td>
							<td>基本項目</td>
						</tr>
						<tr data-name="健康保険" data-calculationType="1日単位" data-usage="使用"
							onclick="fillDeductionItemForm(this)">
							<td>健康保険</td>
							<td>1日単位</td>
							<td><input type="checkbox" checked></td>
							<td>基本項目</td>
						</tr>
						<tr data-name="介護保険" data-calculationType="1日単位" data-usage="使用"
							onclick="fillDeductionItemForm(this)">
							<td>介護保険</td>
							<td>1日単位</td>
							<td><input type="checkbox" checked></td>
							<td>基本項目</td>
						</tr>
						<tr data-name="雇用保険" data-calculationType="1日単位" data-usage="使用"
							onclick="fillDeductionItemForm(this)">
							<td>雇用保険</td>
							<td>1日単位</td>
							<td><input type="checkbox" checked></td>
							<td>基本項目</td>
						</tr>
						<tr data-name="所得税" data-calculationType="1日単位" data-usage="使用"
							onclick="fillDeductionItemForm(this)">
							<td>所得税</td>
							<td>1日単位</td>
							<td><input type="checkbox" checked></td>
							<td>基本項目</td>
						</tr>
						<tr data-name="地方税" data-calculationType="1日単位" data-usage="使用"
							onclick="fillDeductionItemForm(this)">
							<td>地方税</td>
							<td>1日単位</td>
							<td><input type="checkbox" checked></td>
							<td>基本項目</td>
						</tr>
						<tr data-name="葬儀費用" data-calculationType="なし" data-usage="使用"
							onclick="fillDeductionItemForm(this)">
							<td>葬儀費用</td>
							<td>なし</td>
							<td><input type="checkbox" checked></td>
							<td>ユーザー追加項目</td>
						</tr>
						<tr data-name="年末調整" data-calculationType="1日単位"
							data-usage="使用しない" onclick="fillDeductionItemForm(this)">
							<td>年末調整</td>
							<td>1日単位</td>
							<td><input type="checkbox"></td>
							<td>ユーザー追加項目</td>
						</tr>
					</tbody>
				</table>
			</div>

			<!-- 控除項目 入力 -->
			<div class="form-section">
				<div style="height: 40px;"></div> <!-- 원하는 높이만큼 공간 확보 -->
				<label for="deductionItemName">控除項目</label> <input type="text"
					id="deductionItemName" placeholder="控除項目を入力してください"> <label
					for="deductionCalculationType">計算タイプ</label> <select
					id="deductionCalculationType">
					<option value="定数">定数</option>
					<option value="変数">変数</option>
				</select> <label>使用有無</label> <input type="radio" name="deductionItemUsage"
					value="使用" checked> 使用 <input type="radio"
					name="deductionItemUsage" value="使用しない"> 使用しない <label
					for="deductionNote">備考</label> <input type="text"
					id="deductionNote" placeholder="備考を入力してください">

				<div class="form-buttons">
					<button class="btn btn-add">追加</button>
					<button class="btn btn-update">修正</button>
					<button class="btn btn-delete">削除</button>
					<button class="btn btn-clear">内容を消去</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		// ページロード時にサーバーからデータを読み込む（この部分はサーバー側で実装する必要があります）
		// 例えば、サーバーがJSPでデータを送信し、そのデータでテーブルを埋める必要があります。
	</script>
</body>
</html>
