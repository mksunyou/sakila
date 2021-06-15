<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getSales</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<!-- Chart -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		// 카테고리별 매출액 그래프
		$.ajax({
			type: 'get',
			url: '/getSalesByCategory',
			success: function(jsonData){
				//console.log('성공!');
				
				let labels =  new Array();
				let datas = new Array();
				
				$(jsonData).each(function(index, item){
					labels.push(item.category);
					datas.push(item.totalSales);
				});
				
				const data = {
				  labels: labels,
				  datasets: [
				    {
				      label: '카테고리 별 판매량',
				      data: datas,
				      borderColor: 'rgb(255, 99, 132)', //막대 그래프의 테두리 색인거 같습니다.
				      backgroundColor: 'rgb(255,182,193)', //막대 그래프 색깔이에요
				    }
				  ]
				};
				const config = {
						  type: 'bar',
						  data: data,
						  options: {
						    indexAxis: 'y', //'x'로 바꾸면 차트 기준축이 가로가 됩니다.!
						    // Elements options apply to all of the options unless overridden in a dataset
						    // In this case, we are setting the border of each horizontal bar to be 2px wide
						    
						    elements: {
						      bar: {
						        borderWidth: 2,//2는 차트의 테두리 넓이 입니다.
						      }
						    },
						    responsive: true,// 위 데이터의 라벨이 표시될지 정하고 위치를 정할 수 있습니다.
						    plugins: {
						      legend: {
						        position: 'right',
						      },
						      title: {//차트의 제목을 보일지 제목을 뭐로 할지 정할 수 잇습니다.
						        display: true,
						        text: '카테고리별 판매량'
						      }
						    }
						  },
						};
				
				 var myChart = new Chart(
						    document.getElementById('myChart'),
						    config
						  );
			}
		})
		
		// 월별 매출액
		function monthss(){
			$.ajax({
			type: 'get',
			url: '/getSalesByMonth',
			data: {year : $('#yearByMonthSales').val()},
			success: function(jsonData){
				console.log('성공');
				
				//let roots = numbers.map((num) => Math.sqrt(num))
				let store1 =  jsonData.filter(item => item.storeId ==1);
				let store2 = jsonData.filter(item => item.storeId ==2);
				
				console.log(store1);
				
				let storeCount1 = store1.map(item => item.amount);
				let storeCount2 = store2.map(item => item.amount);
				//console.log(storeCount1);
				//let res = users.filter(it => it.name.includes('oli'))
				let date = store1.map(item => item.date);
				console.log(date);
				
				
				const labels = date;
				const data = {
				  labels: labels,
				  datasets: [
				    {
				      label: 'store 1',
				      data: storeCount1,
				      backgroundColor: 'rgb(255, 99, 132)',
				    },
				    {
				      label: 'store 2',
				      data: storeCount2,
				      backgroundColor: 'rgb(54, 162, 235)',
				    }
				  ]
				};
							
				
				const config = {
						  type: 'bar',
						  data: data,
						  options: {
						    plugins: {
						      title: {
						        display: true,
						        text: 'Chart.js Bar Chart - Stacked'
						      },
						    },
						    responsive: true,
						    scales: {
						      x: {
						        stacked:  true,
						      },
						      y: {
						        stacked: true
						      }
						    }
						  }
						};
					
					
					var myChart = new Chart(		
						    document.getElementById('myChart2'),
						    config
						  );
				
					//const DATA_COUNT = 7;
					//const NUMBER_CFG = {count: DATA_COUNT, min: -100, max: 100};
			}
		});
		}
		
		//일별 매출액
		function days(){ $.ajax({
			type: 'get',
			url: '/getSalesByDay',
			data: {year : $('#yearByDaysSales').val(), month: $('#monthByDaysSales').val()},
			success: function(jsonData){
				//console.log('성공');
				console.log(jsonData);
				
				let store1 =  jsonData.filter(item => item.storeId ==1);
				let store2 = jsonData.filter(item => item.storeId ==2);
				
				console.log(store1);
				console.log(store2);
				
				let storeCount1 = store1.map(item => item.amount);
				let storeCount2 = store2.map(item => item.amount);
				console.log(storeCount1);
				//let res = users.filter(it => it.name.includes('oli'))
				let day = store1.map(item => item.day);
				console.log(day);
				
				const labels = day;
				const data = {
				  labels: labels,
				  datasets: [
				    {
				      label: 'store 1',
				      data: storeCount1,
				      borderColor: 'rgb(243,145,166)',
				      backgroundColor: 'rgb(243,140,166)',
				    },
				    {
				      label: 'store 2',
				      data: storeCount2,
				      borderColor: 'rgb(25,25,112)',
				      backgroundColor: 'rgb(120,120, 200)',
				    }
				  ]
				};
					const config = {
					  type: 'line',
					  data: data,
					  options: {
					    responsive: true,
					    plugins: {
					      legend: {
					        position: 'top',
					      },
					      title: {
					        display: true,
					        text: 'Chart.js Line Chart'
					      }
					    }
					  },
					};
					
					var myChart = new Chart(
						    document.getElementById('myChart3'),
						    config 
						  );
			}
		});
		}
	});
</script>
</head>
<body>
	<h3>카테고리별 매출집계</h3>
	
	<div id="span" style="width: 700px; ">
		 <canvas id="myChart"></canvas>
	</div>
	

	<h3>베스트 셀러</h3>


	<table class="table table-striped" style="width: 700px; height:400px">
		<thead>
			<tr>
				<th>제목</th>
				<th>총 대여횟수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bestsellerList}" var="b">
				<tr>
					<td>${b.title}</td>
					<td>${b.cnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h3>월별 매출</h3>
	<div>
		<select id="yearByMonthSales" name="year">
			<option>2005</option>
			<option>2006</option>
		</select>
	</div>
	<div id="span2">
		<canvas id="myChart2"></canvas>
	</div>
	
	
	
	
	<h3>일별 매출</h3>
	<div>
		<select id="yearByDaysSales" name="year">
			<option>2005</option>
			<option>2006</option>
		</select>
		
		<select id="monthByDaysSales" name="year">
			<option>5</option>
			<option>6</option>
			<option>7</option>
			<option>8</option>
		</select>
		<button id="btn">날짜바꾸기</button>
	</div>
	<div id="span3">
		<canvas id="myChart3"></canvas>
	</div>
</body>
</html>