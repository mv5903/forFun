//node JSSystemData.js command to run this file

//Dependencies
const os = require('os');
const nodeDiskInfo = require('node-disk-info');
const gpuinfo = require('gpu-info');
const si = require('systeminformation');
const fs = require('fs');

const path = os.homedir + "/downloads/JS System Data.txt";

var master = "";


//Functions
function toGB(num) {
	return num / 1024.0 / 1024.0 / 1024.0;
}

function toBits(num) {
	return Math.log(num) / Math.log(2);
}

function getCores() {
	return os.cpus().length;
}

function toHMS(seconds) {
	var sec = 0, min = 0, hour = 0;
	if (seconds < 60) {
		return second + " seconds";
	}
	if (seconds < 3600) {
		min = Math.floor(seconds / 60);
		sec = seconds - (min * 60);
		return min + " minutes, " + sec + " seconds";
	}
	hour = Math.floor(seconds / 3600);
	seconds -= (hour * 3600);
	min = Math.floor(seconds / 60);
	sec = seconds - (min * 60);
	return hour + " hours, " + min + " minutes, " + sec + " seconds";
}
//General Information
master+=("*".repeat(30) + "GENERAL INFORMATION" + "*".repeat(30) + "\n\n");
master+=('OS Architecture: ' + os.arch() + "\n");
master+=('CPU: ' + os.cpus()[0].model + "\n");
master+=('Logical Cores: ' + getCores() + "\n");
master+=('Free System RAM: ' + toGB(os.freemem()).toFixed(3) + ' GB' + "\n");
master+=('Total System RAM: ' + toGB(os.totalmem()).toFixed(3) + ' GB' + "\n");
master+=('Home Directory: ' + os.homedir() + "\n");
master+=('Host Name of OS: ' + os.hostname() + "\n");
master+=('OS Platform: ' + os.platform() + "\n");
master+=('OS Realease Version: ' + os.release() + "\n");
master+=('System Temporary Files Directory: ' + os.tmpdir() + "\n");
master+=('Operating System Name: ' + os.type() + "\n");
master+=('System Uptime: ' + toHMS(os.uptime()) + "\n");
master+=('OS Kernel Version: ' + os.version() + "\n" + "\n");
//Disk Information
master+=("*".repeat(30) + "DISK INFORMATION" + "*".repeat(30) + "\n");
try {
	const disks = nodeDiskInfo.getDiskInfoSync();
} catch (e) {
	master+=("Can't get disk information. Skipping...\n\n")
}
const disks = nodeDiskInfo.getDiskInfoSync();
for (const disk of disks) {
	master+=('Drive Letter: ' + disk.mounted);
	master+=('File System: ' + disk.filesystem);
	master+=('Blocks: ' + disk.blocks);
	master+=('Used Space: ' + toGB(disk.used).toFixed(3) + ' GB');
	master+=('Available Space: ' + toGB(disk.available).toFixed(3) + ' GB');
	master+=('Capacity: ' + disk.capacity + '\n');
}
//GPU Information
gpuinfo().then(function(data) {
	for (var i = 0; i < Object.keys(data).length; i++) {
		master+=("*".repeat(30) + "GPU INFORMATION" + "*".repeat(30) + "\n");
		master+=('GPU: ' + data[i].Description);
		master+=('Company: ' + data[i].AdapterCompatibility);
		master+=('Total RAM: ' + (toGB(data[i].AdapterRAM)).toFixed(3) + " GB");
		master+=('Current Resolution: ' + data[i].CurrentHorizontalResolution + ' x ' + data[i].CurrentVerticalResolution);
		master+=('Total Amount of Colors: ' + toBits(data[i].CurrentNumberOfColors));
		master+=('Current Refresh Rate: ' + data[i].CurrentRefreshRate + " Hz");
		master+=('Driver Version: ' + data[i].DriverVersion);
		master+=('Status: ' + data[i].Status);
		master+=('Belongs In: ' + data[i].VideoProcessor + "\n");
	}
});
//CPU Information
si.cpu().then(function(data) {
	console.log("*".repeat(30) + "CPU INFORMATION" + "*".repeat(30) + "\n");
	console.log('Manufacturer: ' + data.manufacturer);
	console.log('Brand: ' + data.brand);
	console.log('Vendor: ' + data.vendor);
	console.log('Family: ' + data.family);
	console.log('Model: ' + data.model);
	console.log('Base Clock Speed: ' + data.speed + " GHz");
	console.log('Cores: ' + data.physicalCores);
	console.log('Logical Processors: ' + data.cores);
	console.log('Processors: ' + data.processors);
	console.log('Socket: ' + data.socket);
	console.log('Cache: ' + JSON.stringify(data.cache) + "\n");
}).catch(error => console.error(error));
//BIOS Information
si.bios().then(function(data) {
	console.log("*".repeat(30) + "BIOS INFORMATION" + "*".repeat(30) + "\n");
	console.log('Vendor: ' + data.vendor);
	console.log('Version: ' + data.version);
	console.log('Release Date: ' + data.releaseDate + "\n");
}).catch(error => console.error(error)); 
//Battery Information
si.battery().then(function(data){
	console.log("*".repeat(30) + "BATTERY INFORMATION" + "*".repeat(30) + "\n");
	console.log('Has Battery: ' + data.hasbattery);
	console.log('Cycle Count: ' + data.cyclecount);
	console.log('Is Charging: ' + data.ischarging);
	console.log('Designed Capactiy: ' + data.designedcapacity);
	console.log('Max Capacity: ' + data.maxcapacity);
	console.log('Percent: ' + data.percent);
	console.log('Is Power Connected: ' + data.acconnected + "\n");
});
//node JSSystemData.node JSSystemData.jsjs command to run this file

fs.writeFile(path, master, function (err) {
	if (err) return console.log(err);
	console.log();
});