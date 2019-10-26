grammar marktex;

document: configs content;

configs: '---' type title author date header_includes '---';
type: 'type' ':' ('article' | 'monography');
title: 'title' ':' STRING;
author: 'author' ':' /* TODO */;
date: 'date' ':' /* TODO */;
header_includes: 'header-includes' ':' /* TODO list */;


content: STRING;