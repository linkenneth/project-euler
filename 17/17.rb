class Fixnum
  @@words = {
    1 => "one",
    2 => "two",
    3 => "three",
    4 => "four",
    5 => "five",
    6 => "six",
    7 => "seven",
    8 => "eight",
    9 => "nine",
    10 => "ten",
    11 => "eleven",
    12 => "twelve",
    13 => "thirteen",
    14 => "fourteen",
    15 => "fifteen",
    16 => "sixteen",
    17 => "seventeen",
    18 => "eighteen",
    19 => "nineteen",
    20 => "twenty",
    30 => "thirty",
    40 => "forty",
    50 => "fifty",
    60 => "sixty",
    70 => "seventy",
    80 => "eighty",
    90 => "ninety",
    0 => ""
  }

  def to_word
    s = self.to_s
    case s.length
    when 3
      other = s[(1..2)].to_i
      if other == 0
        return s[0].to_i.to_word + "hundred"
      else
        return s[0].to_i.to_word + "hundredand" + other.to_word
      end
    when 2, 1
      if @@words.include? self
        return @@words[self]
      end
      i = self - 1
      while i > 0
        if @@words.include? i
          return @@words[i] + (self - i).to_word
        end
        i -= 1
      end
    else
      return "onethousand"
    end
  end
end

(1..5).map do |i|
  i.to_word.length
end.reduce(:+)
