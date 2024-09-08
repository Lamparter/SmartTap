using SmartTap.Sdk;
using System;
using System.Text;
using Microsoft.UI.Xaml;
using Microsoft.UI.Xaml.Controls;
using CommonServiceLocator;

namespace SmartTap.Presentation;

public sealed partial class MainPage : Page
{
    private readonly INfcEmulator _nfcEmulator;

    public MainPage()
    {
        this.InitializeComponent();
        _nfcEmulator = ServiceLocator.Current.GetInstance<INfcEmulator>();
    }

    private async void OnEmulateCardClicked(object sender, RoutedEventArgs e)
    {
        var cardData = Encoding.UTF8.GetBytes("Your NFC Card Data");
        var success = await _nfcEmulator.EmulateCardAsync(cardData);
        if (success)
        {
            await new ContentDialog
            {
                Title = "Success",
                Content = "NFC Card Emulation Started",
                CloseButtonText = "OK"
            }.ShowAsync();
        }
        else
        {
            await new ContentDialog
            {
                Title = "Error",
                Content = "Failed to Start NFC Card Emulation",
                CloseButtonText = "OK"
            }.ShowAsync();
        }
    }
}

