var child_process = require('child_process');
var colors = require('colors');

var result = child_process.exec('node test-out/test');

console.log('» » » Testing:'.bold.yellow);

let output = '';

result.stdout.on('data', function(data) {
  console.log(data.replace('@Success@', '✔ Succcess'.bold.green));
  output += data;
});

result.stderr.on('data', function(data) {
  console.error(data.bold.red);
  output += data;
});

result.on('close', code => {
  let exitCode = output.indexOf('<-=Failure-=>') === -1 ? 0 : 1;
  console.log(exitCode);
  process.exit(exitCode);
});
