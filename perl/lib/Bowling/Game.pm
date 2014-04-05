package Bowling::Game;
use v5.14;
use warnings;

sub new {
    my ($class) = @_;

    my $self = bless {
    }, $class;

    return $self;
}

1;
