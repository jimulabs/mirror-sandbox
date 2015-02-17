if (ARGV.size < 1)
  puts "Usage: create_mock_image_urls.rb <path/to/lorem-ipsum-illustration-repo>"
else
  lorem_path = ARGV[0]
  code = Dir["#{lorem_path}/png/*.png"].inject("") do |code, path|
    if match = path.match(/.+\/(\w+)_(\d+).png$/)
      name, size = match.captures
      url = "https://raw.githubusercontent.com/jimulabs/lorem-ipsum-illustration/master/png/#{name}_#{size}.png"
      code += "MockImage(\\\"#{name}\\\", width=#{size}, height=#{size}, url=\\\"#{url}\\\"), \n"
    end
  end
  puts code
  `echo "#{code}" | pbcopy`
  puts "Code copied to clipboard."
end
