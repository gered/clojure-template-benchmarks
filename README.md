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
* clj-pebble _(soon-to-be-released Clojure wrapper library around [Pebble](https://github.com/mbosecke/pebble))_

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
      <td>0.0897 / 0.0009</td>
      <td>8.4822 / 0.1109</td>
      <td>167.5538 / 1.5672</td>
    </tr>

    <tr>
      <td>Tinsel</td>
      <td>0.1832 / 0.0007</td>
      <td>12.0418 / 0.1884</td>
      <td>236.5161 / 1.3767</td>
    </tr>

    <tr>
      <td>Stencil (string)</td>
      <td>19.0593 / 0.1757</td>
      <td>71.6874 / 0.5020</td>
      <td>526.9889 / 7.2122</td>
    </tr>

    <tr>
      <td>Stencil (file)</td>
      <td>0.6663 / 0.0033</td>
      <td>23.4976 / 0.1265</td>
      <td>476.8262 / 2.1910</td>
    </tr>

    <tr>
      <td>mustache.clj (file)</td>
      <td>0.3394 / 0.0039</td>
      <td>8.1361 / 0.0839</td>
      <td>165.4727 / 1.2440</td>
    </tr>

    <tr>
      <td>Hiccup</td>
      <td>0.1598 / 0.0010</td>
      <td>12.1548 / 0.0950</td>
      <td>237.7211 / 1.0018</td>
    </tr>

    <tr>
      <td>Hiccup (type-hinted)</td>
      <td>0.1731 / 0.0010</td>
      <td>12.8421 / 0.1939</td>
      <td>247.7344 / 1.2166</td>
    </tr>

    <tr>
      <td>Enlive</td>
      <td>13.3319 / 0.0650</td>
      <td>116.5084 / 0.9408</td>
      <td>2141.3317 / 7.9543</td>
    </tr>

    <tr>
      <td>Clabango (string)</td>
      <td>207.5223 / 3.6133</td>
      <td>844.5899 / 12.0383</td>
      <td>9822.2863 / 40.4448</td>
    </tr>

    <tr>
      <td>Clabango (file)</td>
      <td>263.1341 / 5.0118</td>
      <td>907.2633 / 14.9110</td>
      <td>9996.2817 / 57.1756</td>
    </tr>

    <tr>
      <td>Selmer (string)</td>
      <td>22.8039 / 0.0753</td>
      <td>107.4600 / 1.3940</td>
      <td>1138.7408 / 10.4449</td>
    </tr>

    <tr>
      <td>Selmer (file)</td>
      <td>10.7812 / 0.0694</td>
      <td>65.6449 / 0.5891</td>
      <td>1108.8427 / 8.1423</td>
    </tr>

    <tr>
      <td>clj-pebble (string)</td>
      <td>4.9072 / 0.0668</td>
      <td>26.7018 / 0.2296</td>
      <td>442.8252 / 1.9989</td>
    </tr>

    <tr>
      <td>clj-pebble (file)</td>
      <td>4.8032 / 0.0368</td>
      <td>26.8360 / 0.4538</td>
      <td>446.6798 / 2.2262</td>
    </tr>

</table>
