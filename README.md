# clojure-template-benchmarks

Template engines being compared are:

* Clojure `str` function
* [Tinsel](https://github.com/davidsantiago/tinsel)
* [Stencil](https://github.com/davidsantiago/stencil)
* [mustache.clj](https://github.com/shenfeng/mustache.clj)
* [Hiccup](https://github.com/weavejester/hiccup)
* [Enlive](https://github.com/cgrand/enlive)
* [Clabango](https://github.com/danlarkin/clabango)
* [Selmer](https://github.com/yogthos/Selmer)
* [clj-pebble](https://github.com/gered/clj-pebble)

## Data

Times shown are in microseconds (µs) in the format "mean time / standard deviation."

<table>

	<tr>
		<th>Template Engine</th>
		<th>Simple Data Injection</th>
		<th>Small List (50 items)</th>
		<th>Big List (1000 items)</th>
	</tr>

	<tr>
		<td>str</td>
		<td>0.0887 / 0.0010</td>
		<td>8.5493 / 0.0921</td>
		<td>188.9816 / 24.2857</td>
	</tr>
	
	<tr>
		<td>Tinsel</td>
		<td>0.1835 / 0.0010</td>
		<td>12.1433 / 0.0712</td>
		<td>238.9615 / 2.3069</td>
	</tr>
	
	<tr>
		<td>Stencil (string)</td>
		<td>18.2637 / 0.0944</td>
		<td>71.9232 / 0.7373</td>
		<td>527.0062 / 2.6310</td>
	</tr>
	
	<tr>
		<td>Stencil (file)</td>
		<td>0.6550 / 0.0021</td>
		<td>23.4530 / 0.0961</td>
		<td>478.5369 / 3.1301</td>
	</tr>
	
	<tr>
		<td>mustache.clj</td>
		<td>0.3373 / 0.0028</td>
		<td>8.2423 / 0.1148</td>
		<td>167.3133 / 0.7366</td>
	</tr>
	
	<tr>
		<td>Hiccup</td>
		<td>0.1632 / 0.0016</td>
		<td>12.0269 / 0.1069</td>
		<td>236.0784 / 0.5189</td>
	</tr>
	
	<tr>
		<td>Hiccup (type-hinted)</td>
		<td>0.1730 / 0.0013</td>
		<td>12.9494 / 0.0837</td>
		<td>252.9637 / 2.9224</td>
	</tr>
	
	<tr>
		<td>Enlive</td>
		<td>13.6911 / 0.1515</td>
		<td>116.9049 / 1.2055</td>
		<td>2130.2041 / 8.6054</td>
	</tr>
	
	<tr>
		<td>Clabango (string)</td>
		<td>210.0403 / 4.0629</td>
		<td>819.6462 / 9.1964</td>
		<td>9434.1045 / 102.2924</td>
	</tr>
	
	<tr>
		<td>Clabango (file)</td>
		<td>263.1698 / 2.6355</td>
		<td>891.2824 / 11.2746</td>
		<td>9562.9530 / 110.1058</td>
	</tr>
	
	<tr>
		<td>Selmer (string)</td>
		<td>22.7604 / 0.1545</td>
		<td>105.2653 / 0.6792</td>
		<td>1129.9113 / 7.6378</td>
	</tr>
	
	<tr>
		<td>Selmer (file)</td>
		<td>10.6799 / 0.1022</td>
		<td>64.3357 / 0.1904</td>
		<td>1075.8512 / 6.1394</td>
	</tr>
	
	<tr>
		<td>clj-pebble (string)</td>
		<td>2.7430 / 0.0150</td>
		<td>24.3148 / 0.1917</td>
		<td>436.8095 / 5.2781</td>
	</tr>
	
	<tr>
		<td>clj-pebble (file)</td>
		<td>2.7475 / 0.0231</td>
		<td>24.4161 / 0.1602</td>
		<td>440.0397 / 2.1993</td>
	</tr>

</table>
