// Main.java
package com.naviroq.patterns.behavioural.command.remoteControl.demo;

import com.naviroq.patterns.behavioural.command.remoteControl.invoker.RemoteControl;
import com.naviroq.patterns.behavioural.command.remoteControl.command.Command;
import com.naviroq.patterns.behavioural.command.remoteControl.command.fanCommands.*;
import com.naviroq.patterns.behavioural.command.remoteControl.command.garageCommands.*;
import com.naviroq.patterns.behavioural.command.remoteControl.command.lightCommands.*;
import com.naviroq.patterns.behavioural.command.remoteControl.command.macroCommands.MacroCommand;
import com.naviroq.patterns.behavioural.command.remoteControl.command.radioCommands.*;
import com.naviroq.patterns.behavioural.command.remoteControl.command.thermostatCommands.*;
import com.naviroq.patterns.behavioural.command.remoteControl.command.tvCommands.*;
import com.naviroq.patterns.behavioural.command.remoteControl.devices.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RemoteControl remote = new RemoteControl();
        // staticRemote();
        // dynamicRemote();
        buildAndRunMacro(scanner, remote);
    }
    public static void staticRemote() {
        // Create devices
        TV tv = new TV();
        Radio radio = new Radio();
        Light livingRoomLight = new Light("Living Room");
        Fan bedroomFan = new Fan("Bedroom");
        Garage garage = new Garage();
        Thermostat thermostat = new Thermostat();

        // Create remote
        RemoteControl remote = new RemoteControl();

        System.out.println("═══════════════════════════════════════");
        System.out.println("     SMART HOME REMOTE CONTROL");
        System.out.println("═══════════════════════════════════════");

        // Scenario: Movie Night setup
        System.out.println("\n>>> SETTING UP MOVIE NIGHT <<<");
        remote.pressButton(new TVOn(tv));
        remote.pressButton(new TVVolumeLow(tv));
        remote.pressButton(new TVMute(tv));
        remote.pressButton(new LightOff(livingRoomLight));
        remote.pressButton(new FanLow(bedroomFan));

        remote.showStatus();

        // Oops, too quiet. Undo mute
        System.out.println("\n>>> OOPS, TOO QUIET — UNDO MUTE <<<");
        remote.pressUndo();

        // Still too quiet? Redo mute
        System.out.println("\n>>> ACTUALLY, REDO MUTE <<<");
        remote.pressRedo();

        // Undo everything and start over
        System.out.println("\n>>> NAH, LET'S START OVER <<<");
        remote.pressUndo();  // Fan back
        remote.pressUndo();  // Light back on
        remote.pressUndo();  // Mute back
        remote.pressUndo();  // Volume back
        remote.pressUndo();  // TV off

        remote.showStatus();

        // New branch: Leaving home
        System.out.println("\n>>> NEW PLAN: LEAVING HOME <<<");
        remote.pressButton(new GarageOpen(garage));
        remote.pressButton(new GarageClose(garage));
        remote.pressButton(new LightOff(livingRoomLight));
        remote.pressButton(new TVOff(tv));
        remote.pressButton(new RadioOff(radio));
        remote.pressButton(new ThermostatSet(thermostat, 18));

        remote.showStatus();

        // Undo last two
        System.out.println("\n>>> WAIT, DON'T TURN OFF RADIO YET <<<");
        remote.pressUndo();  // Undo thermostat
        remote.pressUndo();  // Undo radio off

        // Check: can we redo? No, because we're about to branch
        System.out.println("\n>>> BRANCHING: TURN ON RADIO INSTEAD <<<");
        remote.pressButton(new RadioOn(radio));
        remote.pressButton(new RadioScan(radio));
        remote.pressButton(new RadioPreset1(radio));

        // Redo stack cleared! Previous redoables gone
        remote.showStatus();

        // Final undo all
        System.out.println("\n>>> UNDO EVERYTHING <<<");
        while (remote.canUndo()) {
            remote.pressUndo();
        }

        System.out.println("\n═══════════════════════════════════════");
        System.out.println("     DEMO COMPLETE");
        System.out.println("═══════════════════════════════════════");
    }

    public static void dynamicRemote() {
        Scanner scanner = new Scanner(System.in);

        // --- Create Devices ---
        TV tv = new TV();
        Radio radio = new Radio();
        Light livingRoom = new Light("Living Room");
        Light kitchen = new Light("Kitchen");
        Light bedroom = new Light("Bedroom");
        Fan bedroomFan = new Fan("Bedroom");
        Fan livingFan = new Fan("Living Room");
        Garage garage = new Garage();
        Thermostat thermostat = new Thermostat();

        // --- Create Remote ---
        RemoteControl remote = new RemoteControl();

        // --- Store commands for easy access ---
        java.util.Map<String, Command> commandMap = new java.util.HashMap<>();

        // TV Commands
        commandMap.put("tv_on", new TVOn(tv));
        commandMap.put("tv_off", new TVOff(tv));
        commandMap.put("tv_vol_up", new TVVolumeUp(tv));
        commandMap.put("tv_vol_down", new TVVolumeDown(tv));
        commandMap.put("tv_vol_low", new TVVolumeLow(tv));
        commandMap.put("tv_vol_max", new TVVolumeMax(tv));
        commandMap.put("tv_mute", new TVMute(tv));
        commandMap.put("tv_standby", new TVStandby(tv));
        commandMap.put("tv_sleep30", new TVSleep30(tv));
        commandMap.put("tv_sleep60", new TVSleep60(tv));

        // Radio Commands
        commandMap.put("radio_on", new RadioOn(radio));
        commandMap.put("radio_off", new RadioOff(radio));
        commandMap.put("radio_vol_up", new RadioVolumeUp(radio));
        commandMap.put("radio_vol_down", new RadioVolumeDown(radio));
        commandMap.put("radio_scan", new RadioScan(radio));
        commandMap.put("radio_preset1", new RadioPreset1(radio));
        commandMap.put("radio_preset2", new RadioPreset2(radio));
        commandMap.put("radio_preset3", new RadioPreset3(radio));
        commandMap.put("radio_preset4", new RadioPreset4(radio));

        // Light Commands
        commandMap.put("light_lr_on", new LightOn(livingRoom));
        commandMap.put("light_lr_off", new LightOff(livingRoom));
        commandMap.put("light_kitchen_on", new LightOn(kitchen));
        commandMap.put("light_kitchen_off", new LightOff(kitchen));
        commandMap.put("light_bed_on", new LightOn(bedroom));
        commandMap.put("light_bed_off", new LightOff(bedroom));

        // Fan Commands
        commandMap.put("fan_bed_high", new FanHigh(bedroomFan));
        commandMap.put("fan_bed_med", new FanMedium(bedroomFan));
        commandMap.put("fan_bed_low", new FanLow(bedroomFan));
        commandMap.put("fan_bed_off", new FanOff(bedroomFan));
        commandMap.put("fan_lr_high", new FanHigh(livingFan));
        commandMap.put("fan_lr_med", new FanMedium(livingFan));
        commandMap.put("fan_lr_low", new FanLow(livingFan));
        commandMap.put("fan_lr_off", new FanOff(livingFan));

        // Garage Commands
        commandMap.put("garage_open", new GarageOpen(garage));
        commandMap.put("garage_close", new GarageClose(garage));

        // Thermostat
        commandMap.put("thermo_set", new ThermostatSet(thermostat, 22)); // default, user sets temp interactively

        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║     🎮 SMART HOME REMOTE (INTERACTIVE MODE)          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");

        while (true) {
            System.out.println("\n╔═══════════════════════════════════════════════════════╗");
            System.out.println("║  COMMAND MENU                                        ║");
            System.out.println("╠═══════════════════════════════════════════════════════╣");
            System.out.println("║  [TV]                                               ║");
            System.out.println("║    1. TV On         2. TV Off      3. Vol Up        ║");
            System.out.println("║    4. Vol Down      5. Mute        6. Standby       ║");
            System.out.println("║    7. Sleep 30min   8. Sleep 60min                  ║");
            System.out.println("║  [RADIO]                                            ║");
            System.out.println("║    9. Radio On      10. Radio Off  11. Vol Up       ║");
            System.out.println("║    12. Vol Down     13. Scan       14. Preset 1     ║");
            System.out.println("║    15. Preset 2     16. Preset 3   17. Preset 4     ║");
            System.out.println("║  [LIGHTS]                                           ║");
            System.out.println("║    18. LR On        19. LR Off                      ║");
            System.out.println("║    20. Kitchen On   21. Kitchen Off                 ║");
            System.out.println("║    22. Bed On       23. Bed Off                     ║");
            System.out.println("║  [FANS]                                             ║");
            System.out.println("║    24. Bed High     25. Bed Med   26. Bed Low       ║");
            System.out.println("║    27. Bed Off      28. LR High   29. LR Med        ║");
            System.out.println("║    30. LR Low       31. LR Off                      ║");
            System.out.println("║  [GARAGE]                                           ║");
            System.out.println("║    32. Garage Open  33. Garage Close                ║");
            System.out.println("║  [THERMOSTAT]                                       ║");
            System.out.println("║    34. Set Thermostat (asks for temp)               ║");
            System.out.println("║  [MACRO]                                            ║");
            System.out.println("║    35. Build & Run Macro                           ║");
            System.out.println("║  [CONTROL]                                          ║");
            System.out.println("║    36. UNDO         37. REDO                       ║");
            System.out.println("║    38. Show Status  39. Exit                       ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝");
            System.out.print("👉 Choose: ");

            String input = scanner.nextLine().trim();

            // --- Handle Control Commands First ---
            if (input.equals("36")) { remote.pressUndo(); continue; }
            if (input.equals("37")) { remote.pressRedo(); continue; }
            if (input.equals("38")) { remote.showStatus(); continue; }
            if (input.equals("39")) {
                System.out.println("👋 Goodbye!");
                break;
            }

            Command cmd = null;
            String key = "";

            // --- Map menu choices to commands ---
            switch (input) {
                // TV
                case "1": key = "tv_on"; break;
                case "2": key = "tv_off"; break;
                case "3": key = "tv_vol_up"; break;
                case "4": key = "tv_vol_down"; break;
                case "5": key = "tv_mute"; break;
                case "6": key = "tv_standby"; break;
                case "7": key = "tv_sleep30"; break;
                case "8": key = "tv_sleep60"; break;
                // Radio
                case "9": key = "radio_on"; break;
                case "10": key = "radio_off"; break;
                case "11": key = "radio_vol_up"; break;
                case "12": key = "radio_vol_down"; break;
                case "13": key = "radio_scan"; break;
                case "14": key = "radio_preset1"; break;
                case "15": key = "radio_preset2"; break;
                case "16": key = "radio_preset3"; break;
                case "17": key = "radio_preset4"; break;
                // Lights
                case "18": key = "light_lr_on"; break;
                case "19": key = "light_lr_off"; break;
                case "20": key = "light_kitchen_on"; break;
                case "21": key = "light_kitchen_off"; break;
                case "22": key = "light_bed_on"; break;
                case "23": key = "light_bed_off"; break;
                // Fans
                case "24": key = "fan_bed_high"; break;
                case "25": key = "fan_bed_med"; break;
                case "26": key = "fan_bed_low"; break;
                case "27": key = "fan_bed_off"; break;
                case "28": key = "fan_lr_high"; break;
                case "29": key = "fan_lr_med"; break;
                case "30": key = "fan_lr_low"; break;
                case "31": key = "fan_lr_off"; break;
                // Garage
                case "32": key = "garage_open"; break;
                case "33": key = "garage_close"; break;
                // Thermostat
                case "34":
                    System.out.print("🌡️ Enter temperature (°C): ");
                    try {
                        int temp = Integer.parseInt(scanner.nextLine());
                        cmd = new ThermostatSet(thermostat, temp);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid temperature. Using 22°C.");
                        cmd = new ThermostatSet(thermostat, 22);
                    }
                    break;
                // Macro
                case "35":
                    buildAndRunMacro(scanner, remote);
                    continue;
                default:
                    System.out.println("❌ Invalid option.");
                    continue;
            }

            // Execute the command if it's not null
            if (cmd != null) {
                remote.pressButton(cmd);
            } else if (!key.isEmpty()) {
                Command c = commandMap.get(key);
                if (c != null) {
                    remote.pressButton(c);
                } else {
                    System.out.println("❌ Command not found.");
                }
            }
        }
    }

    private static void buildAndRunMacro(Scanner scanner, RemoteControl remote) {
        System.out.println("\n📋 ====== MACRO BUILDER ======");
        System.out.println("Enter commands to include in macro (type 'done' when finished):");

        // Show available commands
        System.out.println("\nAvailable commands:");
        System.out.println("  tv_on, tv_off, tv_vol_up, tv_vol_down, tv_mute");
        System.out.println("  radio_on, radio_off, radio_scan, radio_preset1");
        System.out.println("  light_lr_on, light_lr_off, light_kitchen_on, light_kitchen_off");
        System.out.println("  fan_bed_high, fan_bed_med, fan_bed_low, fan_bed_off");
        System.out.println("  garage_open, garage_close");
        System.out.println("  thermo_set (will ask for temp)");
        System.out.println("  ");

        // Map for quick command lookup
        java.util.Map<String, Command> lookup = new java.util.HashMap<>();

        // TV
        TV tv = new TV();
        lookup.put("tv_on", new TVOn(tv));
        lookup.put("tv_off", new TVOff(tv));
        lookup.put("tv_vol_up", new TVVolumeUp(tv));
        lookup.put("tv_vol_down", new TVVolumeDown(tv));
        lookup.put("tv_mute", new TVMute(tv));

        // Radio
        Radio radio = new Radio();
        lookup.put("radio_on", new RadioOn(radio));
        lookup.put("radio_off", new RadioOff(radio));
        lookup.put("radio_scan", new RadioScan(radio));
        lookup.put("radio_preset1", new RadioPreset1(radio));

        // Lights
        Light livingRoom = new Light("Living Room");
        Light kitchen = new Light("Kitchen");
        Light bedroom = new Light("Bedroom");
        lookup.put("light_lr_on", new LightOn(livingRoom));
        lookup.put("light_lr_off", new LightOff(livingRoom));
        lookup.put("light_kitchen_on", new LightOn(kitchen));
        lookup.put("light_kitchen_off", new LightOff(kitchen));

        // Fans
        Fan bedroomFan = new Fan("Bedroom");
        Fan livingFan = new Fan("Living Room");
        lookup.put("fan_bed_high", new FanHigh(bedroomFan));
        lookup.put("fan_bed_med", new FanMedium(bedroomFan));
        lookup.put("fan_bed_low", new FanLow(bedroomFan));
        lookup.put("fan_bed_off", new FanOff(bedroomFan));

        // Garage
        Garage garage = new Garage();
        lookup.put("garage_open", new GarageOpen(garage));
        lookup.put("garage_close", new GarageClose(garage));

        // Thermostat
        Thermostat thermostat = new Thermostat();

        MacroCommand macro = new MacroCommand("Custom Macro");

        while (true) {
            System.out.print("  Add command: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("done")) {
                break;
            }

            if (input.equals("thermo_set")) {
                System.out.print("    Enter temperature: ");
                try {
                    int temp = Integer.parseInt(scanner.nextLine());
                    macro.addCommand(new ThermostatSet(thermostat, temp));
                    System.out.println("    ✅ Added: Thermostat -> " + temp + "°C");
                } catch (NumberFormatException e) {
                    System.out.println("    ❌ Invalid temp. Skipping.");
                }
                continue;
            }

            Command cmd = lookup.get(input);
            if (cmd != null) {
                macro.addCommand(cmd);
                System.out.println("    ✅ Added: " + input);
            } else {
                System.out.println("    ❌ Unknown command: " + input);
            }
        }

        if (macro.getDescription().contains("0 commands")) {
            System.out.println("❌ No commands added. Macro cancelled.");
            return;
        }

        System.out.println("\n📋 Macro built with " + macro.getDescription());
        System.out.print("Run this macro now? (y/n): ");
        String run = scanner.nextLine().trim().toLowerCase();

        if (run.equals("y")) {
            remote.pressButton(macro);
        } else {
            System.out.println("💾 Macro saved but not executed.");
        }
    }
}