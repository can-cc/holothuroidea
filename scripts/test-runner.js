var child_process = require('child_process');
var colors = require('colors');

var result = child_process.exec('node test-out/test');

result.stdout.on('data', function(data) {
  console.log(data);
})

result.stderr.on('data', function(data) {
  console.log(data.bold.red);
})

// console.log(result.toString());
