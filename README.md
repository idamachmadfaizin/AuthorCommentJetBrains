# Author Date Plugin for JetBrains IDEs

## Features
- Insert author tag with a keyboard shortcut (Alt+I, Alt+A).
- Insert date tag with a keyboard shortcut (Alt+I, Alt+D).
- Configurable author name and date format.
- Works with IntelliJ IDEA, PyCharm, WebStorm, and other JetBrains IDEs.

## Installation
1. Download the plugin from JetBrains Marketplace.
2. Install via Settings > Plugins > Install from Disk.

## Configuration
- Go to Settings > Tools > Author Comment Settings.
- Set your preferred author name.
- Customize the date format using Java's DateTimeFormatter patterns.

## Keyboard Shortcut
- Default: Alt+I, Alt+A
  Insert `<author>AuthorName</author>` at the cursors position
- Default: Alt+I, Alt+D
  Insert `<date>EEEE, MMMM d, yyyy</date>`

## Date Format Examples
- `EEEE, MMMM d, yyyy` - Friday, December 6, 2024
- `MM/dd/yyyy` - 12/06/2024
- `dd-MM-yyyy HH:mm` - 06-12-2024 14:30
