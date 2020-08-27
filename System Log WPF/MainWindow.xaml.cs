using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.IO;
using System.Windows.Forms;
using System.Management;
using System.Net.NetworkInformation;

namespace System_Log_WPF
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void DirectoryChooser_Click(object sender, RoutedEventArgs e)
        {
            using (var fbd = new FolderBrowserDialog())
            {
                DialogResult result = fbd.ShowDialog();
                String selectedDirectory = fbd.SelectedPath;
                Console.WriteLine(selectedDirectory);
                DirectoryDisplay.Text = selectedDirectory;
            }
        }

        private void ContinueButton_Click(object sender, RoutedEventArgs e)
        {
            String path = DirectoryDisplay.Text.ToString() + "\\CSharpSystemLog.txt";
            Console.WriteLine(path);
            String[] otherData = { GetDriveInfo(), GetCPUInfo(), GetGPUInfo(), GetDevices(), GetNetworkInfo(), GetOSInfo() };
            Dictionary<String, String> data = GetSystemInfo();
            DirectoryDisplay.Text = "Creating file...";
            WriteToFile(data, otherData, path);
        }

        private void WriteToFile(Dictionary<String, String> data, String[] otherData, String path)
        {
            var file = new FileInfo(path);
            using (StreamWriter sw = file.CreateText())
            {
                String date = "Last Updated: ";
                date += DateTime.UtcNow.ToString("MM-dd-yyyy") + "\t";
                date += DateTime.Now.ToString("h:mm:ss tt") + "\n" + new String('*', 100);
                sw.WriteLine(date);
                foreach (var item in data)
                {
                    sw.WriteLine($"{item.Key} - {item.Value}");
                }
                foreach (var item in otherData)
                {
                    sw.WriteLine(new String('*', 100));
                    sw.WriteLine(item);
                }
            }
            DirectoryDisplay.Text = "Operation Completed";
            ExitButton.Visibility = Visibility.Visible;
        }

        private Dictionary<String, String> GetSystemInfo()
        {
            Dictionary<String, String> data = new Dictionary<String, String>();
            data.Add(nameof(SystemInformation.ActiveWindowTrackingDelay) + ": ", SystemInformation.ActiveWindowTrackingDelay.ToString());
            data.Add(nameof(SystemInformation.ArrangeDirection) + ": ", SystemInformation.ArrangeDirection.ToString());
            data.Add(nameof(SystemInformation.ArrangeStartingPosition) + ": ", SystemInformation.ArrangeStartingPosition.ToString());
            data.Add(nameof(SystemInformation.BootMode) + ": ", SystemInformation.BootMode.ToString());
            data.Add(nameof(SystemInformation.ComputerName) + ": ", SystemInformation.ComputerName.ToString());
            data.Add(nameof(SystemInformation.DbcsEnabled) + ": ", SystemInformation.DbcsEnabled.ToString());
            data.Add(nameof(SystemInformation.HighContrast) + "Enabled: ", SystemInformation.HighContrast.ToString());
            data.Add(nameof(SystemInformation.IsKeyboardPreferred) + ": ", SystemInformation.IsKeyboardPreferred.ToString());
            data.Add(nameof(SystemInformation.IsMenuAnimationEnabled) + ": ", SystemInformation.IsMenuAnimationEnabled.ToString());
            return data;
        }

        private String GetDriveInfo()
        {
            DriveInfo[] allDrives = DriveInfo.GetDrives();
            String master = "";
            foreach (DriveInfo d in allDrives)
            {
                master += ($"Drive {d.Name}\n");
                master += ($"Drive type: {d.DriveType}\n");
                if (d.IsReady)
                {
                    master += ($"Volume label: {d.VolumeLabel}\n");
                    master += ($"File system: {d.DriveFormat}\n");
                    master += ($"Available space to current user: {Math.Round(toGB(d.AvailableFreeSpace), 2)} GB\n");
                    master += ($"Total available space: {Math.Round(toGB(d.TotalFreeSpace), 2)} GB\n");
                    master += ($"Total size of drive: {Math.Round(toGB(d.TotalSize), 2)} GB\n");
                    master += "\n";
                }
            }
            return master;
        }


        private String GetGPUInfo()
        {
            ManagementObjectSearcher myVideoObject = new ManagementObjectSearcher("select * from Win32_VideoController");
            String master = "";
            foreach (ManagementObject obj in myVideoObject.Get())
            {
                master += ("Name  -  " + obj["Name"] + "\n");
                master += ("Status  -  " + obj["Status"] + "\n");
                master += ("Caption  -  " + obj["Caption"] + "\n");
                master += ("DeviceID  -  " + obj["DeviceID"] + "\n");
                master += ("AdapterRAM  -  " + obj["AdapterRAM"] + "\n");
                master += ("AdapterDACType  -  " + obj["AdapterDACType"] + "\n");
                master += ("Monochrome  -  " + obj["Monochrome"] + "\n");
                master += ("InstalledDisplayDrivers  -  " + obj["InstalledDisplayDrivers"] + "\n");
                master += ("DriverVersion  -  " + obj["DriverVersion"] + "\n");
                master += ("VideoProcessor  -  " + obj["VideoProcessor"] + "\n");
                master += ("VideoArchitecture  -  " + obj["VideoArchitecture"] + "\n");
                master += ("VideoMemoryType  -  " + obj["VideoMemoryType"] + "\n\n");
            }

            return master;
        }

        private String GetOSInfo()
        {
            String master = "";
            ManagementObjectSearcher myOperativeSystemObject = new ManagementObjectSearcher("select * from Win32_OperatingSystem");

            foreach (ManagementObject obj in myOperativeSystemObject.Get())
            {
                master += ("Caption  -  " + obj["Caption"] + "\n");
                master += ("WindowsDirectory  -  " + obj["WindowsDirectory"] + "\n");
                master += ("ProductType  -  " + obj["ProductType"] + "\n");
                master += ("SerialNumber  -  " + obj["SerialNumber"] + "\n");
                master += ("SystemDirectory  -  " + obj["SystemDirectory"] + "\n");
                master += ("CountryCode  -  " + obj["CountryCode"] + "\n");
                master += ("CurrentTimeZone  -  " + obj["CurrentTimeZone"] + "\n");
                master += ("EncryptionLevel  -  " + obj["EncryptionLevel"] + "\n");
                master += ("OSType  -  " + obj["OSType"] + "\n");
                master += ("Version  -  " + obj["Version"] + "\n");
            }

            return master;
        }

        private String GetCPUInfo()
        {
            String master = "";
            ManagementObjectSearcher myProcessorObject = new ManagementObjectSearcher("select * from Win32_Processor");

            foreach (ManagementObject obj in myProcessorObject.Get())
            {
                master += ("Name  -  " + obj["Name"] + "\n");
                master += ("DeviceID  -  " + obj["DeviceID"] + "\n");
                master += ("Manufacturer  -  " + obj["Manufacturer"] + "\n");
                master += ("CurrentClockSpeed  -  " + obj["CurrentClockSpeed"] + "\n");
                master += ("Caption  -  " + obj["Caption"] + "\n");
                master += ("NumberOfCores  -  " + obj["NumberOfCores"] + "\n");
                master += ("NumberOfEnabledCore  -  " + obj["NumberOfEnabledCore"] + "\n");
                master += ("NumberOfLogicalProcessors  -  " + obj["NumberOfLogicalProcessors"] + "\n");
                master += ("Architecture  -  " + obj["Architecture"] + "\n");
                master += ("Family  -  " + obj["Family"] + "\n");
                master += ("ProcessorType  -  " + obj["ProcessorType"] + "\n");
                master += ("Characteristics  -  " + obj["Characteristics"] + "\n");
                master += ("AddressWidth  -  " + obj["AddressWidth"] + "\n");
            }

            return master;
        }

        private String GetNetworkInfo()
        {
            String master = "";
            NetworkInterface[] nics = NetworkInterface.GetAllNetworkInterfaces();

            if (nics == null || nics.Length < 1)
            {
                master += ("  No network interfaces found.\n");
            }
            else
            {
                foreach (NetworkInterface adapter in nics)
                {
                    IPInterfaceProperties properties = adapter.GetIPProperties();
                    master += "\n";
                    master += (adapter.Description);
                    master += (String.Empty.PadLeft(adapter.Description.Length, '=') + "\n");
                    master += ("  Interface type .......................... : {0}", adapter.NetworkInterfaceType) + "\n";
                    master += ("  Physical Address ........................ : {0}", adapter.GetPhysicalAddress().ToString()) + "\n";
                    master += ("  Operational status ...................... : {0}", adapter.OperationalStatus) + "\n";
                }
            }

            return master;
        }

        private String GetDevices()
        {
            String master = "";

            ManagementObjectSearcher myAudioObject = new ManagementObjectSearcher("select * from Win32_SoundDevice");

            foreach (ManagementObject obj in myAudioObject.Get())
            {
                master += ("Name  -  " + obj["Name"] + "\n");
                master += ("ProductName  -  " + obj["ProductName"] + "\n");
                master += ("Availability  -  " + obj["Availability"] + "\n");
                master += ("DeviceID  -  " + obj["DeviceID"] + "\n");
                master += ("PowerManagementSupported  -  " + obj["PowerManagementSupported"] + "\n");
                master += ("Status  -  " + obj["Status"] + "\n");
                master += ("StatusInfo  -  " + obj["StatusInfo"] + "\n");
                master += (String.Empty.PadLeft(obj["ProductName"].ToString().Length, '=')) + "\n";
            }

            return master;
        }


        private double toGB(long bytes)
        {
            return bytes / 1024.0 / 1024.0 / 1024.0;
        }

        private void ExitButton_Click(object sender, RoutedEventArgs e)
        {
            DirectoryDisplay.Text = "Exiting...";
            Environment.Exit(0);
        }
    }
}
