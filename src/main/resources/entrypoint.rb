#!/usr/bin/env ruby
# frozen_string_literal: true
require 'license_finder'
command = "license_finder " + ARGV.join(' ')
system(command)
