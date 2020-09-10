import psutil
import platform
import sys
from datetime import datetime
from tkinter import Tk
from tkinter.filedialog import askdirectory
from psutil import virtual_memory


#Ask user for folder location and create File Writer

Tk().withdraw() # we don't want a full GUI, so keep the root window from appearing
path = askdirectory()
path+="/PythonSysInfo.txt"
original_stdout = sys.stdout
#Convert bytes to a given unit

def get_size(bytes, suffix="B"):
	factor = 1024;
	for unit in ["", "K", "M", "G", "T", "P"]:
		if bytes < factor:
			return f"{bytes:.2f}{unit}"
		bytes/=factor

def get_percent(used, total):
    x = used / total * 100
    return f"{x:.2f}"

def get_cpu_type():
    from win32com.client import GetObject
    root_winmgmts = GetObject("winmgmts:root\cimv2")
    cpus = root_winmgmts.ExecQuery("Select * from Win32_Processor")
    return cpus[0].Name

#Variale Imports
uname = platform.uname()
boot_time_timestamp = psutil.boot_time()
bt = datetime.fromtimestamp(boot_time_timestamp)
cpufreq = psutil.cpu_freq()
partitions = psutil.disk_partitions()
disk_io = psutil.disk_io_counters()
if_addrs = psutil.net_if_addrs()
net_io = psutil.net_io_counters()
mem = virtual_memory()

#Add information
with open(path, "w") as f:
    sys.stdout = f
    print("="*40, "System Information", "="*40)
    print(f"System: {uname.system}")
    print(f"Node Name: {uname.node}")
    print(f"Release: {uname.release}")
    print(f"Version: {uname.version}")
    print(f"Machine: {uname.machine}")
    print(f"Processor: {get_cpu_type()} ({psutil.cpu_count(logical=False)} cores)")
    print(f"RAM: {get_size(mem.available)}/{get_size(mem.total)} ({get_percent(mem.available, mem.total)}%)")

    print("="*40, "Boot Time", "="*40)
    print(f"Boot Time: {bt.year}/{bt.month}/{bt.day} {bt.hour}:{bt.minute}:{bt.second}")

    print("="*40, "CPU Information", "="*40)
    print("Physical cores:", psutil.cpu_count(logical=False))
    print("Total cores:", psutil.cpu_count(logical=True))
    print(f"Max frequency: {cpufreq.max:.2f}Mhz")
    print(f"Min frequency: {cpufreq.min:.2f}Mhz")
    print("CPU Usage Per Core:")
    for i, percentage in enumerate(psutil.cpu_percent(percpu=True, interval=1)):
        print(f"Core {i}: {percentage}%")
    print(f"Total CPU uage: {psutil.cpu_percent()}%")

    print("="*40, "Disk Information", "="*40)
    print("Partitions and Usage:")
    for partition in partitions:
        print(f"=== Device: {partition.device} ===")
        print(f"  Mountpoint: {partition.mountpoint}")
        print(f"  File system type: {partition.fstype}")
        try:
            partition_usage = psutil.disk_usage(partition.mountpoint)
        except PermissionError:
            continue
        print(f"  Total Size: {get_size(partition_usage.total)}")
        print(f"  Used: {get_size(partition_usage.used)}")
        print(f"  Free: {get_size(partition_usage.free)}")
        print(f"  Percentage: {partition_usage.percent}%")
    print(f"Total read: {get_size(disk_io.read_bytes)}")
    print(f"Total write: {get_size(disk_io.write_bytes)}")


    print("="*40, "Network Information", "="*40)
    for interface_name, interface_addresses in if_addrs.items():
        for address in interface_addresses:
            print(f"=== Interface: {interface_name} ===")
            if str(address.family) == 'AddressFamily.AF_INET':
                print(f"  IP Address: {address.address}")
                print(f"  Netmask: {address.netmask}")
                print(f"  Broadcast IP: {address.broadcast}")
            elif str(address.family) == 'AddressFamily.AF_PACKET':
                print(f"  MAC Address: {address.address}")
                print(f"  Netmask: {address.netmask}")
                print(f"  Broadcast MAC: {address.broadcast}")

    print(f"Total Bytes Sent: {get_size(net_io.bytes_sent)}")
    print(f"Total Bytes Received: {get_size(net_io.bytes_recv)}")
    sys.stdout = original_stdout



