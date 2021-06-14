#!/bin/bash

service ssh restart >> /status.log
tail -f /status.log