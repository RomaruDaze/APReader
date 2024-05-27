# Access Point Command Executor

This program is designed to execute commands on a specified Access Point (AP) and log the output results to a selected folder as a `.txt` file. The program retrieves the username and password required to access the AP from a CSV file.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Example](#example)
- [License](#license)

## Prerequisites

- Python 3.x
- JDK-21_windows-x64

## Installation

- Clone this repository:
  ```sh
  git clone https://github.com/RomaruDaze/APReader.git
  cd APReader
  ```

## Usage

1. Prepare a CSV file with the following columns: `username`, `password`. Each row should contain the credentials for a different AP.

2. Run the program with the command you wish to execute and the directory where you want to save the log files:
    ```sh
    java -jar APReader/src/AP.jar
    ```


## License

Feel free to customize this `README.md` file to better fit your project's specifics and additional details.



---
# アクセスポイントコマンドエグゼキュータ

このプログラムは、指定されたアクセスポイント（AP）でコマンドを実行し、結果を選択したフォルダに`.txt`ファイルとしてログ出力するためのものです。APにアクセスするために必要なユーザー名とパスワードはCSVファイルから取得します。

## 目次

- [前提条件](#前提条件)
- [インストール](#インストール)
- [使用方法](#使用方法)
- [ライセンス](#ライセンス)

## 前提条件

- Python 3.x
- JDK-21_windows-x64

## インストール

- このリポジトリをクローンします：
  ```sh
  git clone https://github.com/RomaruDaze/APReader.git
  cd APReader
  ```

## 使用方法

1. 以下の列を持つCSVファイルを準備します： `username`, `password`。各行には異なるAPの資格情報を含めます。

2. 実行したいコマンドとログファイルを保存したいディレクトリを指定してプログラムを実行します：
    ```sh
    java -jar APReader/src/AP.jar
    ```

## ライセンス

この`README.md`ファイルを、プロジェクトの詳細や追加情報に合わせて自由にカスタマイズしてください。
