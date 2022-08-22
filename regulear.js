function regularExpStr(str) {
    var pattern=/(.*)(SelfUpdate)(.*)/
    var reg = pattern.exec(str);
    return (reg[2] + reg[3]);
}