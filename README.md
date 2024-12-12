# Author Date Plugin for JetBrains IDEs

## Features
- Insert author and date tags with a keyboard shortcut (Ctrl+Alt+A).
- Configurable author name and date format.
- Works with IntelliJ IDEA, PyCharm, WebStorm, and other JetBrains IDEs.

## Installation
1. Download the plugin from JetBrains Marketplace.
2. Install via Settings > Plugins > Install from Disk.

## Configuration
- Go to Settings > Author Comment Settings.
- Set your preferred author name.
- Customize the date format using Java's DateTimeFormatter patterns.

## Keyboard Shortcut
- Default: Ctrl+Alt+A
- Inserts `<author>AuthorName</author><date>Formatted Date</date>` at the cursors position

## Date Format Examples
- `EEEE, MMMM d, yyyy` - Friday, December 6, 2024
- `MM/dd/yyyy` - 12/06/2024
- `dd-MM-yyyy HH:mm` - 06-12-2024 14:30
