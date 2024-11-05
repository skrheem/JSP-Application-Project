<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<!-- 김찬호 金燦鎬 -->
<!-- 휴가근태설정 休暇勤怠設定 -->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>休暇 / 勤怠 設定</title>
<!-- CSS ファイル リンク -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/KyuukaKintaiKoumoku.css">
<!-- JS ファイル リンク -->
<script
	src="${pageContext.request.contextPath}/js/KyuukaKintaiKoumoku.js"></script>
</head>
<body>
	<div class="container">
		<h1>休暇 / 勤怠 設定</h1>

		<!-- 休暇項目と勤怠項目の設定を横並びに表示するラッパー -->
		<div class="section-wrapper">
			<!-- 休暇項目 設定 -->
			<div class="table-section">
				<h2>休暇項目 設定</h2>
				<table>
					<thead>
						<tr>
							<th>休暇項目</th>
							<th>適用期間</th>
							<th>事業別 休暇日数</th>
							<th>使用有無</th>
						</tr>
					</thead>
					<tbody>
						<tr data-name="2014_年次" data-start="2014-01-01"
							data-end="2014-12-31" data-usage="使用" onclick="fillForm(this)">
							<td>2014_年次</td>
							<td>2014-01-01 ~ 2014-12-31</td>
							<td><button class="btn btn-gray"
									onclick="openVacationModal()">管理</button></td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="2015_報奨休暇" data-start="2015-01-01"
							data-end="2015-12-31" data-usage="使用" onclick="fillForm(this)">
							<td>2015_報奨休暇</td>
							<td>2015-01-01 ~ 2015-12-31</td>
							<td><button class="btn btn-gray"
									onclick="openVacationModal()">管理</button></td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="2015_年次" data-start="2015-01-01"
							data-end="2015-12-31" data-usage="使用" onclick="fillForm(this)">
							<td>2015_年次</td>
							<td>2015-01-01 ~ 2015-12-31</td>
							<td><button class="btn btn-gray"
									onclick="openVacationModal()">管理</button></td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="2016_年次" data-start="2016-01-01"
							data-end="2016-12-31" data-usage="使用" onclick="fillForm(this)">
							<td>2016_年次</td>
							<td>2016-01-01 ~ 2016-12-31</td>
							<td><button class="btn btn-gray"
									onclick="openVacationModal()">管理</button></td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="2016_報奨休暇" data-start="2016-01-01"
							data-end="2016-12-31" data-usage="使用" onclick="fillForm(this)">
							<td>2016_報奨休暇</td>
							<td>2016-01-01 ~ 2016-12-31</td>
							<td><button class="btn btn-gray"
									onclick="openVacationModal()">管理</button></td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="2017_年次" data-start="2017-01-01"
							data-end="2017-12-31" data-usage="使用" onclick="fillForm(this)">
							<td>2017_年次</td>
							<td>2017-01-01 ~ 2017-12-31</td>
							<td><button class="btn btn-gray"
									onclick="openVacationModal()">管理</button></td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="2017_報奨休暇" data-start="2017-01-01"
							data-end="2017-12-31" data-usage="使用しない" onclick="fillForm(this)">
							<td>2017_報奨休暇</td>
							<td>2017-01-01 ~ 2017-12-31</td>
							<td><button class="btn btn-gray"
									onclick="openVacationModal()">管理</button></td>
							<td><span class="usage-status">使用しない</span></td>
						</tr>

					</tbody>
				</table>
			</div>

			<!-- 休暇項目 入力 폼 -->
			<div class="form-section">
				<div style="height: 45px;"></div> <!-- 원하는 높이만큼 공간 확보 -->
				<label for="vacationItem">休暇項目</label> <input type="text"
					id="vacationItem" name="vacationItem" placeholder="休暇項目を入力してください">

				<label for="startDate">適用期間 開始</label> <input type="date"
					id="startDate" name="startDate"> <label for="endDate">適用期間
					終了</label> <input type="date" id="endDate" name="endDate"> <label>使用有無</label>
				<input type="radio" name="attendanceUsage" value="使用" checked>
				使用 <input type="radio" name="attendanceUsage" value="使用しない">
				使用しない

				<div class="form-buttons">
					<button class="btn btn-add">追加</button>
					<button class="btn btn-update">修正</button>
					<button class="btn btn-delete">削除</button>
					<button class="btn btn-clear">内容を消去</button>
				</div>
			</div>
		</div>

		<!-- 勤怠項目とその入力を横並びに表示するラッパー -->
		<div class="section-wrapper">
			<!-- 勤怠項目 設定 -->
			<div class="table-section">
				<h2>勤怠項目 設定</h2>
				<table>
					<thead>
						<tr>
							<th>勤怠項目</th>
							<th>単位</th>
							<th>グループ管理</th>
							<th>休暇控除</th>
							<th>使用有無</th>
						</tr>
					</thead>
					<tbody>
						<tr data-name="年次" data-unit="日" data-group="休暇"
							data-vacation="2017_年次" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>年次</td>
							<td>日</td>
							<td>休暇</td>
							<td>2017_年次</td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="半日" data-unit="日" data-group="休暇"
							data-vacation="2017_年次" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>半日</td>
							<td>日</td>
							<td>休暇</td>
							<td>2017_年次</td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="遅刻" data-unit="時間" data-group="遅刻早退"
							data-vacation="-" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>遅刻</td>
							<td>時間</td>
							<td>遅刻早退</td>
							<td>-</td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="早退" data-unit="時間" data-group="遅刻早退"
							data-vacation="-" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>早退</td>
							<td>時間</td>
							<td>遅刻早退</td>
							<td>-</td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="外勤" data-unit="時間" data-group="その他"
							data-vacation="-" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>外勤</td>
							<td>時間</td>
							<td>その他</td>
							<td>-</td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="休日勤務" data-unit="時間" data-group="延長勤務"
							data-vacation="-" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>休日勤務</td>
							<td>時間</td>
							<td>延長勤務</td>
							<td>-</td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="延長勤務" data-unit="時間" data-group="延長勤務"
							data-vacation="-" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>延長勤務</td>
							<td>時間</td>
							<td>延長勤務</td>
							<td>-</td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="報奨休暇" data-unit="日" data-group="休暇"
							data-vacation="2017_報奨休暇" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>報奨休暇</td>
							<td>日</td>
							<td>休暇</td>
							<td>2017_報奨休暇</td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="夜間勤務" data-unit="時間" data-group="延長勤務"
							data-vacation="-" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>夜間勤務</td>
							<td>時間</td>
							<td>延長勤務</td>
							<td>-</td>
							<td><span class="usage-status">使用</span></td>
						</tr>
						<tr data-name="請願休暇" data-unit="日" data-group="休暇"
							data-vacation="-" data-usage="使用"
							onclick="fillAttendanceForm(this)">
							<td>請願休暇</td>
							<td>日</td>
							<td>休暇</td>
							<td>-</td>
							<td><span class="usage-status">使用</span></td>
						</tr>

					</tbody>
				</table>
			</div>

			<!-- 勤怠項目 入力 폼 -->
			<div class="form-section">
				<div style="height: 45px;"></div> <!-- 원하는 높이만큼 공간 확보 -->
				<label for="attendanceItem">勤怠項目</label> <input type="text"
					id="attendanceItem" placeholder="勤怠項目を入力してください"> <label
					for="unit">単位</label> <select id="unit">
					<option value="日">日</option>
					<option value="時間">時間</option>
				</select> <label for="groupManagement">勤怠グループ</label>
				<div style="display: flex; align-items: center;">
					<select id="groupManagement" style="flex: 1; margin-right: 5px;">
						<option>選択してください。</option>
						<option value="休暇">休暇</option>
						<option value="延長勤務">延長勤務</option>
						<option value="遅刻早退">遅刻早退</option>
						<option value="特勤">特勤</option>
						<option value="その他">その他</option>
					</select>
					<button class="btn btn-gray" onclick="openGroupModal()">管理</button>
				</div>

				<label for="vacationType">休暇控除</label> <select id="vacationType">
					<option>選択してください。</option>
					<option value="2014_年次">2014_年次</option>
					<option value="2015_報奨休暇">2015_報奨休暇</option>
					<option value="2016_年次">2016_年次</option>
					<option value="2017_報奨休暇">2017_報奨休暇</option>

				</select> <label>使用有無</label> <input type="radio" name="attendanceUsage"
					value="使用" checked> 使用 <input type="radio"
					name="attendanceUsage" value="使用しない"> 使用しない

				<div class="form-buttons">
					<button class="btn btn-add">追加</button>
					<button class="btn btn-update">修正</button>
					<button class="btn btn-delete">削除</button>
					<button class="btn btn-clear">内容を消去</button>
				</div>
			</div>
		</div>
		<!-- 社員別 休暇日数設定モーダル -->
		<div id="vacationModal" class="modal">
			<div class="modal-content">
				<div class="modal-header">
					<h2>休暇日数 設定</h2>
					<button class="close-btn" onclick="closeVacationModal()">&times;</button>
				</div>
				<div class="modal-body">
					<input type="text" id="shainSearch" placeholder="社員 検索">
					<button class="btn btn-gray">全体表示</button>
					<table>
						<thead>
							<tr>
								<th><input type="checkbox"></th>
								<th>区分</th>
								<th>社員番号</th>
								<th>氏名</th>
								<th>部署</th>
								<th>職位</th>
								<th>入社日</th>
								<th>休暇日数</th>
							</tr>
						</thead>
						<tbody>
							<!-- 社員データが動的に追加されます。 -->
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button class="btn btn-delete">休暇日数 削除</button>
					<button class="btn btn-save">休暇日数 保存</button>
					<button class="btn btn-calculate">年次休暇計算方法</button>
					<button class="btn btn-calculate" onclick="calculateVacationDays()">休暇日数
						自動計算</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
