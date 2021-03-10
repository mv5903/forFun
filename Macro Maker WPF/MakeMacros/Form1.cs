using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MakeMacros
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            createInitialItems();
        }

        public void createInitialItems()
        {
            //Initialize Welcome Text
            Label welcome = new Label()
            {
                Text = "Welcome to Macro Maker!",
                Font = new Font("Comic Sans MS", 36),
                Location = new Point(120, 20),
                TabIndex = 10,
                AutoSize = true
            };
            Controls.Add(welcome);


            //Add your first macro button
            Button addFirstMacro = new Button()
            {
                Text = "Click me to set up or change the shortcut for your Macro keys.",
                Font = new Font("Comic Sans MS", 16),
                Location = new Point(130, 300),
                TabIndex = 10,
                AutoSize = true
                
            };
            addFirstMacro.Click += new EventHandler(addFirstMacro_Click);
            Controls.Add(addFirstMacro);
            TextBox showMasterMacro = new TextBox()
            {
                Text = "Keybind not yet set!",
                Font = new Font("Comic Sans MS", 12),
                Location = new Point(400, 400),
                TabIndex = 10,
                AutoSize = true
            };
            showMasterMacro.ScrollBars = ScrollBars.None;

            // Make the TextBox fit its initial text.
            Controls.Add(showMasterMacro);
        }

       

        private void addFirstMacro_Click(object sender, System.EventArgs e)
        {
            
            
            Console.WriteLine("Test Message");
        }

        
    }
}
